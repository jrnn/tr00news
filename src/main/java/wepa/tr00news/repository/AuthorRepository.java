package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);

}