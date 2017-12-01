package wepa.tr00news.service;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.FileObject;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.FileObjectRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articles;
    @Autowired
    private FileObjectRepository fileObjects;

    @Transactional
    public Article save(
            MultipartFile pic,
            String headline,
            String lead,
            String body
    ) throws IOException
    {
        Article a = new Article();
        a.setHeadline(headline);
        a.setLead(lead);
        a.setBody(body);
        a.setPublishedOn(LocalDateTime.now());
        a = this.articles.save(a);

        FileObject f = new FileObject();
        f.setContent(pic.getBytes());
        f.setArticle(a);
        f = this.fileObjects.save(f);

        a.setPicture(f);
        return this.articles.save(a);
    }

}