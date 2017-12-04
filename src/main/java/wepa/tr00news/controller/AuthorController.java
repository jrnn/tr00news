package wepa.tr00news.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Author;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.AuthorRepository;

@Controller
public class AuthorController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors/{id}")
    public String getAuthor(@PathVariable Long id, Model model) {
        /*
        To-do: Service or Repository component has method .findUnassignedArticles(Author author)
        */
        Author author = authorRepository.getOne(id);
        List<Article> articles = articleRepository.findAll()
                .stream()
                .filter(a -> !a.getAuthors().contains(author))
                .collect(Collectors.toList());

        model.addAttribute("author", author);
        model.addAttribute("articles", articles);

        return "author";
    }

    @PostMapping("/authors")
    public String postAuthor(@RequestParam String name) {
        /*
        To-do: Service component has methods .save() and .update()
        */
        Author author = authorRepository.findByName(name.trim());

        if (author != null) {
            return "redirect:/admin";
        }

        author = new Author();
        author.setName(name.trim());
        authorRepository.save(author);

        return "redirect:/admin";
    }

    @PostMapping("/authors/{id}/articles")
    @Transactional
    public String assignArticleToAuthor(
            @PathVariable Long id,
            @RequestParam Long articleId
    ) {
        /*
        To-do: Service component has method .assignArticleToAuthor(article, author)
        */
        Author author = authorRepository.getOne(id);
        Article article = articleRepository.getOne(articleId);

        if (author != null && article != null) {
            author.getArticles().add(article);
            article.getAuthors().add(author);
        }

        return "redirect:/authors/" + author.getId();
    }

}