package by.scooter.dao;

import by.scooter.api.dao.ScooterDAO;
import by.scooter.dto.vehicle.ScooterFilterDTO;
import by.scooter.entity.vehicle.Scooter;
import by.scooter.entity.vehicle.Scooter_;
import by.scooter.exception.VacantScooterNotFound;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;

@Log4j2
@Repository
public class ScooterDAOImpl extends AbstractDAO<Scooter> implements ScooterDAO {

    private static final String VACANT_SCOOTERS = """
            SELECT id, odometer, currentPoint_id, model_id
            FROM scooters
            	LEFT JOIN (SELECT scooter_id
            			   FROM orders
                           WHERE (:evStart BETWEEN eventStart AND eventEnd
            				   OR :evEnd BETWEEN eventStart AND eventEnd
            				   OR eventStart BETWEEN :evStart AND :evEnd
            				   OR eventEnd BETWEEN :evStart AND :evEnd ))
            			   AS intersecting_orders
            	ON scooter_id = scooters.id
            	WHERE scooter_id is null
            	AND currentPoint_id = :point
                AND model_id = :model
                ORDER BY :sort""";

    @Override
    public List<Scooter> getVacant(ScooterFilterDTO filter, Integer page, Integer size) {
        if (filter.getSortedColumn() == null) {
            filter.setSortedColumn(Scooter_.ODOMETER);
        }

        var query = entityManager.createNativeQuery(VACANT_SCOOTERS, Scooter.class)
                .setParameter("evStart", filter.getEventStart())
                .setParameter("evEnd", filter.getEventEnd())
                .setParameter("point", filter.getRentPointId())
                .setParameter("model", filter.getScooterModelId())
                .setParameter("sort", filter.getSortedColumn());

        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        var res = query.getResultList();
        if (res.isEmpty()) {
            log.info("No available scooters id: {} model in rent point {}",
                    filter.getScooterModelId(), filter.getRentPointId());
            throw new VacantScooterNotFound("No available scooters");
        }
        return (List<Scooter>) res;
    }

    @Override
    protected Class<Scooter> getClazz() {
        return Scooter.class;
    }
}
