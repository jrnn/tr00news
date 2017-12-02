package wepa.tr00news.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Picture;

@Service
public class PictureService {

    private static final String DEFAULT_PICTURE_URL =
            "https://www.cs.helsinki.fi/u/jjuurine/default.jpg";

    public Picture convertToPicture(MultipartFile f) {
        try {
            if (f.getContentType().contains("image") && f.getSize() > 0) {
                Picture p = new Picture();
                p.setContent(f.getBytes());

                return p;
            }
        } catch (IOException e) {
        }

        return null;
    }

    public Picture getDefaultPicture() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] b = new byte[4096];
            int bytesRead;
            InputStream is = new URL(DEFAULT_PICTURE_URL).openStream();

            while ((bytesRead = is.read(b)) > 0) {
                baos.write(b, 0, bytesRead);
            }

            Picture p = new Picture();
            p.setContent(baos.toByteArray());

            return p;
        } catch (Exception e) {
        }

        return null;
    }

}