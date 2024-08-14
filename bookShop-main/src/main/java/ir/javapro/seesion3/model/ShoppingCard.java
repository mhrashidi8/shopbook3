package ir.javapro.seesion3.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(schema = SchemaName.SchemaName)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCard extends BaseEntity {

    private int count;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Factor factor;
}
