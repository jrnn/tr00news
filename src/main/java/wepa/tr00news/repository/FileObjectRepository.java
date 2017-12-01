package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.FileObject;

public interface FileObjectRepository extends JpaRepository<FileObject, Long> {
}