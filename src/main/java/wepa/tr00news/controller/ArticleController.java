package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    public String getAddArticle(Model model) {
        return "article";
    }

    @GetMapping("/articles/{id}")
    public String getEditArticle(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));

        return "article";
    }

    @PostMapping("/articles")
    public String postAddArticle(
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file
    ) {
        article = articleService.saveArticle(article);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPictureToArticle(article, picture);

        return "redirect:/articles/" + article.getId();
    }

    @PostMapping("/articles/{id}")
    public String postEditArticle(
            @PathVariable Long id,
            @ModelAttribute Article article,
            @RequestParam(value = "file") MultipartFile file
    ) {
        article = articleService.updateArticle(id, article);
        Picture picture = pictureService.savePicture(file);
        articleService.assignPictureToArticle(article, picture);

        return "redirect:/articles/" + article.getId();
    }

    @GetMapping(
            path = "/articles/{id}/picture",
            produces = {"image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"}
    )
    @ResponseBody
    public byte[] getPicture(@PathVariable Long id) {
        return articleRepository.getOne(id).getPicture().getContent();
    }

}