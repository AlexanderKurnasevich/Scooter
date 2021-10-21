package by.scooter.entity.event;

import by.scooter.entity.user.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Positive;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return getClient().equals(order.getClient()) && getPrice().equals(order.getPrice()) && getMileage().equals(order.getMileage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getClient(), getPrice(), getMileage());
    }
}
