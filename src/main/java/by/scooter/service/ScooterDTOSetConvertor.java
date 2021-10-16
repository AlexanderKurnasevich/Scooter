package by.scooter.service;

import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.entity.location.RentPoint;
import by.scooter.entity.vehicle.Scooter;
import by.scooter.entity.vehicle.ScooterModel;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;

public class ScooterDTOSetConvertor extends AbstractConverter<Set<ScooterDTO>, Set<Scooter>> {

    @Override
    protected Set<Scooter> convert(Set<ScooterDTO> scooterSet) {
        Set<Scooter> res = new HashSet<>();
        for(ScooterDTO scooter : scooterSet) {
            Scooter sc = new Scooter();
            ScooterModel scooterModel = new ScooterModel();
            RentPoint rentPoint = new RentPoint();
            scooterModel.setId(scooter.getModelId());
            rentPoint.setId(scooter.getCurrentPointId());
            sc.setId(scooter.getId());
            sc.setModel(scooterModel);
            sc.setCurrentPoint(rentPoint);
            sc.setOdometer(scooter.getOdometer());
            boolean b = res.contains(sc);
            res.add(sc);
        }
        return res;
    }
}