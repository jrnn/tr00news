package wepa.tr00news.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    private String lead;
    private String body;
    private LocalDateTime publishedOn;

    @OneToOne(mappedBy = "article", fetch = FetchType.LAZY)
    private Picture picture;

    @ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
    private List<Author> authors;

    @ManyToMany(mappedBy = "articles", fetch = FetchType.LAZY)
    private List<Topic> topics;

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