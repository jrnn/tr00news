package wepa.tr00news.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import wepa.tr00news.domain.Article;
import wepa.tr00news.repository.ArticleRepository;

@Service
public class NewsService {

    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getLatestArticles(int howMany) {
        return articleRepository.findAll(
                PageRequest.of(0, howMany, Sort.Direction.DESC, "publishedOn"))
                .getContent();
    }

    public List<Article> getArticlesByTopic(Long topicId) {
        return articleRepository.findAllByTopic(topicId)
                .stream()
                .sorted((a1, a2) -> a2.getPublishedOn().compareTo(a1.getPublishedOn()))
                .collect(Collectors.toList());
    }

    public List<Article> getPopularArticles() {
        List<Article> articles = articleRepository.findAll()
                .stream()
                .sorted((a1, a2) -> a2.getPublishedOn().compareTo(a1.getPublishedOn()))
                .sorted((a1, a2) -> a2.getRecentClicks() - a1.getRecentClicks())
                .collect(Collectors.toList());

        if (articles.size() > 10) {
            articles = articles.subList(0, 9);
        }

        return articles;
    }

}