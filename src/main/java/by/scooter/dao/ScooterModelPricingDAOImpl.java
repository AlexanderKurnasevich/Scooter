package by.scooter.dao;

import by.scooter.api.dao.ScooterModelPricingDAO;
import by.scooter.entity.pricing.ScooterModelPricing;
import org.springframework.stereotype.Repository;

@Repository
public class ScooterModelPricingDAOImpl extends AbstractDAO<ScooterModelPricing> implements ScooterModelPricingDAO {
    @Override
    protected Class<ScooterModelPricing> getClazz() {
        return ScooterModelPricing.class;
    }
}
