package wepa.tr00news.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Article extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    private String headline;
    @Column(columnDefinition = "LONGTEXT")
    private String lead;
    @Column(columnDefinition = "LONGTEXT")
    private String body;
    private LocalDateTime publishedOn;

    @OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
    private Picture picture;

    @OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
    private List<Click> clicks;

    @ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
    private List<Author> authors;

    @ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
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