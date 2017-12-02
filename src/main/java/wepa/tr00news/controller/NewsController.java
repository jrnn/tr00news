package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import wepa.tr00news.repository.ArticleRepository;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articles;

    @GetMapping("/news")
    public String get(Model m) {
        m.addAttribute("articles", this.articles.findAll(
                PageRequest.of(0, 5, Sort.Direction.DESC, "publishedOn")
        ));

        return "news";
    }

}