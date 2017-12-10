package wepa.tr00news.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Click")
public class Click extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "session_id")
    private String sessionId;

    @Column(name = "happened_on")
    private LocalDateTime happenedOn;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public boolean isRecent() {
        LocalDateTime weekAgo = LocalDateTime.now().minusWeeks(1);

        return getHappenedOn().isAfter(weekAgo);
    }

}