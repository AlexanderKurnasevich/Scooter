package by.scooter.entity.event;

import by.scooter.entity.AbstractEntity;
import by.scooter.entity.vehicle.Scooter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Event extends AbstractEntity {

    @Column(nullable = false)
    protected LocalDateTime eventStart;

    @Column(nullable = false)
    protected LocalDateTime eventEnd;

    @ManyToOne
    protected Scooter scooter;
}
