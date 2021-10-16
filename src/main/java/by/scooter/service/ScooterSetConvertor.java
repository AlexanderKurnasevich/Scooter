package by.scooter.service;

import by.scooter.entity.dto.vehicle.ScooterDTO;
import by.scooter.entity.vehicle.Scooter;
import org.modelmapper.AbstractConverter;

import java.util.HashSet;
import java.util.Set;

public class ScooterSetConvertor extends AbstractConverter<Set<Scooter>, Set<ScooterDTO>> {

    @Override
    protected Set<ScooterDTO> convert(Set<Scooter> scooterSet) {
        Set<ScooterDTO> res = new HashSet<>();
        for(Scooter scooter : scooterSet) {
            res.add(ScooterDTO.builder()
                    .id(scooter.getId())
                    .modelId(scooter.getModel().getId())
                    .currentPointId(scooter.getCurrentPoint().getId())
                    .odometer(scooter.getOdometer())
                    .build());
        }
        return res;
    }
}
