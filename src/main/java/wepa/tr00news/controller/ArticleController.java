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
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.service.ArticleService;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepo;

    @GetMapping("/admin/articles")
    public String addGet(Model model) {
        return "article";
    }

    @GetMapping("/admin/articles/{id}")
    public String editGet(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleRepo.getOne(id));

        return "article";
    }

    @PostMapping("/admin/articles")
    public String addPost(
            @ModelAttribute Article a,
            @RequestParam(value = "pic") MultipartFile f
    ) {
        a = this.articleService.save(a, f);

        return "redirect:/admin/articles/" + a.getId();
    }

    @PostMapping("/admin/articles/{id}")
    public String editPost(
            @PathVariable Long id,
            @ModelAttribute Article a,
            @RequestParam(value = "pic") MultipartFile f
    ) {
        a = this.articleService.update(id, a, f);

        return "redirect:/admin/articles/" + a.getId();
    }

    @GetMapping(path = "/articles/{id}/picture",
            produces = {"image/jpg", "image/jpeg", "image/png", "image/gif"})
    @ResponseBody
    public byte[] pictureGet(@PathVariable Long id) {
            return this.articleRepo.getOne(id).getPicture().getContent();
    }

}