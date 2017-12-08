package wepa.tr00news.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Article")
public class Article extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "headline")
    private String headline;

    @Column(name = "lead", columnDefinition = "TEXT")
    private String lead;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @Column(name = "published_on")
    private LocalDateTime publishedOn;

    @OneToOne(mappedBy = "article")
    private Picture picture;

    @OneToMany(mappedBy = "article")
    private List<Click> clicks;

    @ManyToMany(mappedBy = "articles")
    private List<Author> authors;

    @ManyToMany(mappedBy = "articles")
    private List<Topic> topics;

    public int getClickCount() {
        return getClicks().size();
    }

    public String getDate() {
        StringBuilder sb = new StringBuilder();
        LocalDateTime d = getPublishedOn();

        sb.append(d.getYear()).append(".");

        if (d.getMonthValue() < 10) {
            sb.append("0");
        }

        sb.append(d.getMonthValue()).append(".");

        if (d.getDayOfMonth() < 10) {
            sb.append("0");
        }

        sb.append(d.getDayOfMonth());
        return sb.toString();
    }

}