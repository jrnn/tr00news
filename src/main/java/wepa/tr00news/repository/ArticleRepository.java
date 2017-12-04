package wepa.tr00news.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wepa.tr00news.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Query("SELECT a FROM Article a WHERE a.id NOT IN " +
            "( SELECT ar.id FROM Article ar JOIN ar.authors au WHERE au.id = :id )")
    List<Article> findUnassignedToAuthor(@Param("id") Long authorId);

    @Query("SELECT a FROM Article a WHERE a.id NOT IN " +
            "( SELECT ar.id FROM Article ar JOIN ar.topics t WHERE t.id = :id )")
    List<Article> findUnassignedToTopic(@Param("id") Long topicId);

}