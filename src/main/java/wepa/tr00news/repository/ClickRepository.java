package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.Click;

public interface ClickRepository extends JpaRepository<Click, Long> {
}