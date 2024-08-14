package ir.javapro.seesion3.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(schema = SchemaName.SchemaName)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Factor extends BaseEntity {


    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    private Payed payed;

}
