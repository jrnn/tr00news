package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.service.ClickService;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClickService clickService;

    @GetMapping("/news")
    public String getNewsHome(Model model) {
        model.addAttribute("articles", articleRepository.findAll(
                PageRequest.of(0, 5, Sort.Direction.DESC, "publishedOn")
        ));

        return "news";
    }

    @GetMapping("/news/{id}")
    public String getArticle(Model model, @PathVariable Long id) {
        model.addAttribute("article", articleRepository.getOne(id));
        clickService.addClickToArticle(id);

        return "article_view";
    }

}