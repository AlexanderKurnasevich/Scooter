package by.scooter.entity.event;

import by.scooter.entity.user.Client;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends Event {

    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(nullable = false, columnDefinition = "float default 0.0")
    @PositiveOrZero
    private Float price;

    @Column(nullable = false)
    @PositiveOrZero
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
