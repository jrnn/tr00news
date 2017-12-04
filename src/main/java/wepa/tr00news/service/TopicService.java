package wepa.tr00news.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Topic;
import wepa.tr00news.repository.TopicRepository;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public Topic saveTopic(String name) {
        Topic topic = topicRepository.findByNameIgnoreCase(name);

        if (name.isEmpty() || topic != null) {
            return null;
        }

        topic = new Topic();
        topic.setName(name);

        return topicRepository.save(topic);
    }

    public Topic updateTopic(Long id, String name) {
        Topic topic = topicRepository.getOne(id);

        if (name.isEmpty() || topic == null) {
            return null;
        }

        topic.setName(name);
        return topicRepository.save(topic);
    }

    @Transactional
    public void assignArticleToTopic(Topic topic, Article article) {
        if (topic == null || article == null
                || topic.getArticles().contains(article)
                || article.getTopics().contains(topic)) {
            return;
        }

        topic.getArticles().add(article);
        article.getTopics().add(topic);
    }

    @Transactional
    public void removeArticleFromTopic(Topic topic, Article article) {
        topic.getArticles().remove(article);
        article.getTopics().remove(topic);
    }

}