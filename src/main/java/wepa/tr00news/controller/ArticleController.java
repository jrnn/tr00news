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
    private ArticleService articleService;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles")
    public String getAdd(Model model) {
        return "article";
    }

    @GetMapping("/articles/{id}")
    public String getEdit(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleRepository.getOne(id));

        return "article";
    }

    @PostMapping("/articles")
    public String postAdd(
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file
    ) {
        article = articleService.saveArticle(article);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPicture(article, picture);

        return "redirect:/articles/" + article.getId();
    }

    @PostMapping("/articles/{id}")
    public String postEdit(
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file,
            @PathVariable Long id
    ) {
        article = articleService.updateArticle(article, id);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPicture(article, picture);

        return "redirect:/articles/" + article.getId();
    }

}