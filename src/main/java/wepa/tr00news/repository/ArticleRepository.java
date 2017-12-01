package wepa.tr00news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wepa.tr00news.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}