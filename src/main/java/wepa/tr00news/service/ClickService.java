package wepa.tr00news.service;

import java.time.LocalDateTime;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Click;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.ClickRepository;

@Service
public class ClickService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ClickRepository clickRepository;
    @Autowired
    private HttpSession session;

    @Transactional
    public void addClickToArticle(Long id) {
        Article article = articleRepository.getOne(id);

        if (article == null) {
            return;
        }

        Click click = new Click();
        click.setSessionId(session.getId());
        click.setHappenedOn(LocalDateTime.now());
        click = clickRepository.save(click);

        click.setArticle(article);
        article.getClicks().add(click);
    }

}