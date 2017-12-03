package wepa.tr00news.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Picture;
import wepa.tr00news.repository.PictureRepository;

@Service
public class PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    public Picture savePicture(MultipartFile file) {
        Picture picture = convertToPicture(file);

        if (picture == null) {
            return null;
        }

        return pictureRepository.save(picture);
    }

    public Picture convertToPicture(MultipartFile file) {
        try {
            if (file.getContentType().contains("image") && file.getSize() > 0) {
                Picture picture = new Picture();
                picture.setContent(file.getBytes());

                return picture;
            }
        } catch (IOException e) {
        }

        return null;
    }

}