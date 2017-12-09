package wepa.tr00news.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "Picture")
public class Picture extends AbstractPersistable<Long> {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    //@Lob // <-- causes issues with heroku postgres
    @Column(name = "content")
    private byte[] content;

    @OneToOne
    @JoinColumn(name = "article_id")
    private Article article;

}