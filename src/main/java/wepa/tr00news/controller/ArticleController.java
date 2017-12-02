package wepa.tr00news.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Article;
import wepa.tr00news.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articles;

    @GetMapping("/admin/articles")
    public String get(Model m) {
        return "add";
    }

    @PostMapping("/admin/articles")
    public String post(
            @RequestParam(value = "pic", required = true) MultipartFile pic,
            @RequestParam(required = true) String headline,
            @RequestParam(required = true) String lead,
            @RequestParam(required = true) String body
    ) throws IOException
    {
        if (!headline.trim().isEmpty() &&
                !lead.trim().isEmpty() &&
                !body.trim().isEmpty() &&
                pic.getContentType().contains("image"))
        {
            this.articles.save(pic, headline.trim(), lead.trim(), body.trim());
        }

        return "redirect:/admin/articles";
    }

}