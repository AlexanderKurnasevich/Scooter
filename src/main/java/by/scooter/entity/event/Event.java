package by.scooter.entity.event;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
}
