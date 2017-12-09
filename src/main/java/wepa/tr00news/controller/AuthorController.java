package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Author;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.AuthorRepository;
import wepa.tr00news.service.AuthorService;

@Controller
public class AuthorController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorService authorService;

    @GetMapping("/admin/authors/{id}")
    public String view(Model model, @PathVariable Long id) {
        model.addAttribute("author", authorRepository.getOne(id));
        model.addAttribute("articles", articleRepository.findUnassignedToAuthor(id));

        return "author";
    }

    @PostMapping("/admin/authors")
    public String add(@RequestParam String name) {
        authorService.saveAuthor(name.trim());

        return "redirect:/admin";
    }

    @PostMapping("/admin/authors/{id}")
    public String rename(
            @PathVariable Long id,
            @RequestParam String name
    ) {
        authorService.updateAuthor(id, name.trim());

        return "redirect:/admin/authors/" + id;
    }

    @PostMapping("/admin/authors/{id}/articles")
    public String assignArticle(
            @PathVariable Long id,
            @RequestParam Long articleId
    ) {
        Author author = authorRepository.getOne(id);
        Article article = articleRepository.getOne(articleId);
        authorService.assignArticleToAuthor(author, article);

        return "redirect:/admin/authors/" + author.getId();
    }

    @DeleteMapping("/admin/authors/{authorId}/articles/{articleId}")
    public String removeArticle(
            @PathVariable Long authorId,
            @PathVariable Long articleId
    ) {
        Author author = authorRepository.getOne(authorId);
        Article article = articleRepository.getOne(articleId);
        authorService.removeArticleFromAuthor(author, article);

        return "redirect:/admin/authors/" + author.getId();
    }

}