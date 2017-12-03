package wepa.tr00news.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}