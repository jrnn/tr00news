package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wepa.tr00news.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("SELECT a FROM Author a WHERE LOWER(a.name) = LOWER(:name)")
    Author findByNameIgnoreCase(@Param("name")String name);

}