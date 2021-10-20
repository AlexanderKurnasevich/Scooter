package by.scooter.entity.event;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@MappedSuperclass
public abstract class Event extends AbstractEntity {

    @Column(nullable = false)
    protected LocalDateTime eventStart;

    @Column(nullable = false)
    protected LocalDateTime eventEnd;

    @ManyToOne
    protected Scooter scooter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Event)) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return getEventStart().equals(event.getEventStart()) && getEventEnd().equals(event.getEventEnd()) && getScooter().equals(event.getScooter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getEventStart(), getEventEnd(), getScooter());
    }
}
