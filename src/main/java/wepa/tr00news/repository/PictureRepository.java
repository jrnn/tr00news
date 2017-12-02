package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}