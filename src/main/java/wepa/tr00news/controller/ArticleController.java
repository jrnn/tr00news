package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Picture;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.service.ArticleService;
import wepa.tr00news.service.PictureService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private PictureService pictureService;

    @GetMapping("/admin/articles")
    public String view(Model model) {
        return "admin_article";
    }

    @GetMapping("/admin/articles/{id}")
    public String edit(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));

        return "admin_article";
    }

    @PostMapping("/admin/articles")
    public String add(
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file
    ) {
        article = articleService.saveArticle(article);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPictureToArticle(article, picture);

        return "redirect:/admin/articles/" + article.getId();
    }

    @PostMapping("/admin/articles/{id}")
    public String update(
            @PathVariable Long id,
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file
    ) {
        article = articleService.updateArticle(id, article);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPictureToArticle(article, picture);

        return "redirect:/admin/articles/" + article.getId();
    }

}