package by.scooter.entity.event;

import by.scooter.entity.user.User;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "maintenances")
public class Maintenance extends Event {

    @ManyToOne
    private User user;
}
