package wepa.tr00news.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Topic extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
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