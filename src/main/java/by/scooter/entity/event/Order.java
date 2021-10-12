package by.scooter.entity.event;

import by.scooter.entity.user.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Event {

    @ManyToOne
    private Client client;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    @Positive
    private Float price;

    @Column(nullable = false)
    @Positive
    private Integer mileage; //in meters
}
