package ir.javapro.seesion3.model;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(schema = SchemaName.SchemaName)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "deleted is null")
@SQLDelete(sql = "update shop.book set deleted = now() where id = ?")
public class Book extends BaseEntity {

    private String name;
    private long price;
}
