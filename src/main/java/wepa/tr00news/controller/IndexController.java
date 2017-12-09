package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.AuthorRepository;
import wepa.tr00news.repository.TopicRepository;

@Controller
public class IndexController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private TopicRepository topicRepository;

    @RequestMapping("*")
    public String handleDefault() {
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String viewAdminMain(Model model) {
        model.addAttribute("articles", articleRepository.findAll());
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("topics", topicRepository.findAll());

        return "admin";
    }

}