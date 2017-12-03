package wepa.tr00news.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Picture extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Lob
    private byte[] content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Article article;

}