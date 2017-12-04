package wepa.tr00news.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Topic;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.TopicRepository;

@Controller
public class TopicController {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/topics/{id}")
    public String getTopic(@PathVariable Long id, Model model) {
        /*
        To-do: Service or Repository component has method .findUnassignedArticles(Topic topic)
        */
        Topic topic = topicRepository.getOne(id);
        List<Article> articles = articleRepository.findAll()
                .stream()
                .filter(a -> !a.getTopics().contains(topic))
                .collect(Collectors.toList());

        model.addAttribute("topic", topic);
        model.addAttribute("articles", articles);

        return "topic";
    }

    @PostMapping("/topics")
    public String postTopic(@RequestParam String name) {
        /*
        To-do: Service component has methods .save() and .update()
        */
        Topic topic = topicRepository.findByName(name.trim());

        if (topic != null) {
            return "redirect:/admin";
        }

        topic = new Topic();
        topic.setName(name.trim());
        topicRepository.save(topic);

        return "redirect:/admin";
    }

    @PostMapping("/topics/{id}/articles")
    @Transactional
    public String assignArticleToTopic(
            @PathVariable Long id,
            @RequestParam Long articleId
    ) {
        /*
        To-do: Service component has method .assignArticleToAuthor(article, author)
        */
        Topic topic = topicRepository.getOne(id);
        Article article = articleRepository.getOne(articleId);

        if (topic != null && article != null) {
            topic.getArticles().add(article);
            article.getTopics().add(topic);
        }

        return "redirect:/topics/" + topic.getId();
    }

}