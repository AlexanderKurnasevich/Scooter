package by.scooter.dao;

import by.scooter.api.dao.DiscountDAO;
import by.scooter.entity.pricing.Discount;
import by.scooter.entity.pricing.Discount_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DiscountDAOImpl extends AbstractDAO<Discount> implements DiscountDAO {

    @Override
    public Discount getByPromoCode(String promoCode) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Discount> criteriaQuery = builder.createQuery(getClazz());
        Root<Discount> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(Discount_.PROMO_CODE), promoCode));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    protected Class<Discount> getClazz() {
        return Discount.class;
    }
}
