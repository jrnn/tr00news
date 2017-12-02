package wepa.tr00news.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Article;
import wepa.tr00news.domain.Picture;
import wepa.tr00news.repository.ArticleRepository;
import wepa.tr00news.repository.PictureRepository;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepo;
    @Autowired
    private PictureRepository pictureRepo;
    @Autowired
    private PictureService pictureService;

    @Transactional
    public Article save(Article a, MultipartFile f) {
        a = this.trimArticle(a);
        a.setPublishedOn(LocalDateTime.now());

        Picture p = this.pictureService.convertToPicture(f);

        if (p == null) {
            p = this.pictureService.getDefaultPicture();
        }

        p.setArticle(a);
        p = this.pictureRepo.save(p);
        a.setPicture(p);

        return this.articleRepo.save(a);
    }

    @Transactional
    public Article update(Long id, Article aNew, MultipartFile f) {
        Article a = this.articleRepo.getOne(id);

        if (a == null) {
            return this.save(aNew, f);
        }

        aNew = this.trimArticle(aNew);
        a.setHeadline(aNew.getHeadline());
        a.setLead(aNew.getLead());
        a.setBody(aNew.getBody());

        Picture p = this.pictureService.convertToPicture(f);

        if (p != null) {
            p.setArticle(a);
            p = this.pictureRepo.save(p);

            this.pictureRepo.delete(a.getPicture());
            a.setPicture(p);
        }

        return this.articleRepo.save(a);
    }

    private Article trimArticle(Article a) {
        a.setHeadline(a.getHeadline().trim());
        a.setLead(a.getLead().trim());
        a.setBody(a.getBody().trim());

        if (a.getHeadline().isEmpty()) {
            a.setHeadline("[Headline missing!]");
        }
        if (a.getLead().isEmpty()) {
            a.setLead("[Lead paragraph missing!]");
        }
        if (a.getBody().isEmpty()) {
            a.setBody("[Running text missing!]");
        }

        return a;
    }

}