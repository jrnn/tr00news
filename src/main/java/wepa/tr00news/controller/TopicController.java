package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Topic;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.TopicRepository;
import wepa.tr00news.service.TopicService;

@Controller
public class TopicController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicService topicService;

    @GetMapping("/topics/{id}")
    public String viewTopic(Model model, @PathVariable Long id) {
        model.addAttribute("topic", topicRepository.getOne(id));
        model.addAttribute("articles", articleRepository.findUnassignedToTopic(id));

        return "topic";
    }

    @PostMapping("/topics")
    public String addTopic(@RequestParam String name) {
        topicService.saveTopic(name.trim());

        return "redirect:/admin";
    }

    @PostMapping("/topics/{id}")
    public String renameTopic(
            @PathVariable Long id,
            @RequestParam String name
    ) {
        topicService.updateTopic(id, name.trim());

        return "redirect:/topics/" + id;
    }

    @PostMapping("/topics/{id}/articles")
    public String assignArticleToTopic(
            @PathVariable Long id,
            @RequestParam Long articleId
    ) {
        Topic topic = topicRepository.getOne(id);
        Article article = articleRepository.getOne(articleId);
        topicService.assignArticleToTopic(topic, article);

        return "redirect:/topics/" + topic.getId();
    }

    @DeleteMapping("/topics/{topicId}/articles/{articleId}")
    public String removeArticleFromTopic(
            @PathVariable Long topicId,
            @PathVariable Long articleId
    ) {
        Topic topic = topicRepository.getOne(topicId);
        Article article = articleRepository.getOne(articleId);
        topicService.removeArticleFromTopic(topic, article);

        return "redirect:/topics/" + topic.getId();
    }

}