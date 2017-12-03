package wepa.tr00news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import wepa.tr00news.repository.ArticleRepository;

@Controller
public class PictureController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping(
            path = "/articles/{id}/picture",
            produces = {"image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp"}
    )
    @ResponseBody
    public byte[] getPicture(@PathVariable Long id) {
        return articleRepository.getOne(id).getPicture().getContent();
    }

}