package wepa.tr00news.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Picture;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.PictureRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PictureRepository pictureRepository;

    public Article saveArticle(Article article) {
        article = trimArticle(article);
        article.setPublishedOn(LocalDateTime.now());

        return articleRepository.save(article);
    }

    public Article updateArticle(Long id, Article newArticle) {
        Article article = articleRepository.getOne(id);

        if (article == null) {
            return saveArticle(newArticle);
        }

        newArticle = trimArticle(newArticle);
        article.setHeadline(newArticle.getHeadline());
        article.setLead(newArticle.getLead());
        article.setBody(newArticle.getBody());

        return articleRepository.save(article);
    }

    @Transactional
    public void assignPictureToArticle(Article article, Picture picture) {
        if (article == null || picture == null) {
            return;
        }

        if (article.getPicture() != null) {
            pictureRepository.delete(article.getPicture());
        }

        picture.setArticle(article);
        article.setPicture(picture);
    }

    private Article trimArticle(Article article) {
        article.setHeadline(article.getHeadline().trim());
        article.setLead(article.getLead().trim());
        article.setBody(article.getBody().trim());

        if (article.getHeadline().isEmpty()) {
            article.setHeadline("[Headline missing!!]");
        }
        if (article.getLead().isEmpty()) {
            article.setLead("[Lead paragraph missing!!]");
        }
        if (article.getBody().isEmpty()) {
            article.setBody("[Running text missing!!]");
        }

        return article;
    }

}