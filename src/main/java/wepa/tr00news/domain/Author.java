package wepa.tr00news.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Author")
public class Author extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany
    @JoinTable(name = "ArticleAuthor",
            joinColumns =
                    @JoinColumn(name = "article_id", referencedColumnName = "id"),
            inverseJoinColumns =
                    @JoinColumn(name = "author_id", referencedColumnName = "id")
    )
    private List<Article> articles;

    public int getArticleCount() {
        return getArticles().size();
    }

    public int getClickCount() {
        return getArticles()
                .stream()
                .mapToInt(a -> a.getClickCount())
                .sum();
    }

}