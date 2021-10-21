package by.scooter.dao;

import by.scooter.api.dao.DAO;
import by.scooter.entity.AbstractEntity;
import by.scooter.entity.AbstractEntity_;
import by.scooter.exception.DAOException;
import lombok.extern.log4j.Log4j2;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public abstract class AbstractDAO<T extends AbstractEntity> implements DAO<T> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected AbstractDAO() {
    }

    @Override
    public T getById(Long id) {
        return entityManager.find(getClazz(), id);
    }

    @Override
    public T save(T entity) {
        try {
            entityManager.persist(entity);
        } catch (Exception e) {
            log.warn(e);
            throw new DAOException("Save failed");
        }
        return entity;
    }

    @Override
    public void delete(Long id) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<T> criteriaQuery = builder.createCriteriaDelete(getClazz());
        Root<T> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.where(builder.equal(entityRoot.get(AbstractEntity_.ID), id));
        try {
            entityManager.createQuery(criteriaQuery).executeUpdate();
        } catch (Exception e) {
            log.warn(e);
            throw new DAOException("Delete failed");
        }
    }

    @Override
    public void update(T entity) {
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            log.warn(e);
            throw new DAOException("Update failed");
        }
    }

    @Override
    public List<T> getAll() {
        return getAll(null, null);
    }

    @Override
    public List<T> getAll(Integer page, Integer size) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = builder.createQuery(getClazz());
        Root<T> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(entityRoot);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        if (page != null && size != null) {
            query.setFirstResult((page - 1) * size);
            query.setMaxResults(size);
        }
        return query.getResultList();
    }

    @Override
    public Long getAllCount() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
        Root<T> entityRoot = criteriaQuery.from(getClazz());
        criteriaQuery.select(builder.count(entityRoot));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public void saveAll(List<T> list) {
        for (T entity : list) {
            save(entity);
        }
    }

    protected abstract Class<T> getClazz();
}
