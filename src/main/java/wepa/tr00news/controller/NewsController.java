package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.TopicRepository;
import wepa.tr00news.service.ClickService;
import wepa.tr00news.service.NewsService;

@Controller
public class NewsController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClickService clickService;
    @Autowired
    private NewsService newsService;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/news")
    public String viewMain(Model model) {
        model = addCommonAttributes(model);
        model.addAttribute("articles", newsService.getLatestArticles(5));

        return "news";
    }

    @GetMapping("/news/{id}")
    public String viewArticle(Model model, @PathVariable Long id) {
        model = addCommonAttributes(model);
        model.addAttribute("article", articleRepository.getOne(id));
        clickService.addClickToArticle(id);

        return "news_article";
    }

    @GetMapping("/news/topics/{id}")
    public String viewTopic(Model model, @PathVariable Long id) {
        model = addCommonAttributes(model);
        model.addAttribute("topic", topicRepository.getOne(id));
        model.addAttribute("articles", newsService.getArticlesByTopic(id));

        return "news_topic";
    }

    private Model addCommonAttributes(Model model) {
        model.addAttribute("topics", topicRepository.findAll());
        model.addAttribute("latest", newsService.getLatestArticles(10));
        model.addAttribute("popular", newsService.getPopularArticles());

        return model;
    }

}