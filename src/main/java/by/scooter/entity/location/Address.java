package by.scooter.entity.location;

import by.scooter.entity.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name="addresses",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"prefix","street","number","postfix", "city_id"})
)
public class Address extends AbstractEntity {

    @Column(nullable = false)
    private String prefix;

    @Column(nullable = false)
    private String street;

    @Column
    private Short number;

    @Column
    private String postfix;

    @ManyToOne
    private City city;
}