package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import wepa.tr00news.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    @Query("SELECT t FROM Topic t WHERE LOWER(t.name) = LOWER(:name)")
    Topic findByNameIgnoreCase(@Param("name") String name);

}