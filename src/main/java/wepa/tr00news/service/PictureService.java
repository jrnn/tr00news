package wepa.tr00news.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wepa.tr00news.domain.Picture;

@Service
public class PictureService {

    private static final String DEFAULT_PICTURE_URL =
            "https://www.cs.helsinki.fi/u/jjuurine/default.jpg";

    public Picture convertPicture(MultipartFile f) {
        Picture p = new Picture();

        try {
            if (f.getContentType().contains("image") && f.getSize() > 0) {
                p.setContent(f.getBytes());

                return p;
            }
        } catch (IOException e) {
        }

        try {
            byte[] b = this.getDefaultPicture(new URL(DEFAULT_PICTURE_URL));
            p.setContent(b);

            return p;
        } catch (MalformedURLException e) {
        }

        return null;
    }

    private byte[] getDefaultPicture(URL url) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] b = new byte[4096];
            int bytesRead;
            InputStream is = url.openStream();

            while ((bytesRead = is.read(b)) > 0) {
                baos.write(b, 0, bytesRead);
            }

            return baos.toByteArray();
        } catch (IOException e) {
        }

        return null;
    }

}