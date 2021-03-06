package by.scooter.dao;

import by.scooter.api.dao.OrderDAO;
import by.scooter.entity.AbstractEntity_;
import by.scooter.entity.event.Event_;
import by.scooter.entity.event.Order;
import by.scooter.entity.event.Order_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDAOImpl extends AbstractDAO<Order> implements OrderDAO {

    @Override
    public List<Order> getOrdersByScooter(Long id, Integer page, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(getClazz());
        Root<Order> entityRoot = criteriaQuery.from(getClazz());

        criteriaQuery.select(entityRoot)
                .where(builder.equal(entityRoot.get(Event_.SCOOTER).get(AbstractEntity_.ID), id));

        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    public List<Order> getByClient(Long id, Integer page, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = builder.createQuery(getClazz());
        Root<Order> entityRoot = criteriaQuery.from(getClazz());

        criteriaQuery.select(entityRoot).where(builder.equal(entityRoot.get(Order_.CLIENT).get(AbstractEntity_.ID), id));

        TypedQuery<Order> query = entityManager.createQuery(criteriaQuery);
        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }

        return query.getResultList();
    }

    @Override
    protected Class<Order> getClazz() {
        return Order.class;
    }
}
