package by.scooter.dao;

import by.scooter.api.dao.SpecialModelPricingDAO;
import by.scooter.entity.pricing.SpecialModelPricing;
import org.springframework.stereotype.Repository;

@Repository
public class SpecialModelPricingDAOImpl extends AbstractDAO<SpecialModelPricing> implements SpecialModelPricingDAO {
    @Override
    protected Class<SpecialModelPricing> getClazz() {
        return SpecialModelPricing.class;
    }
}
