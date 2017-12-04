package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

    Topic findByName(String name);

}