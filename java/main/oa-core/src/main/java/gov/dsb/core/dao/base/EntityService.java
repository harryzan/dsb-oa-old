package gov.dsb.core.dao.base;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-1-8
 * Time: 14:52:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class EntityService<T, PK extends Serializable> {

    private EntityDao<T, PK> entityDao;

    private SqlDao sqlDao;

    public void initDao(SessionFactory sessionFactory, Class<T> entityClass) {
        entityDao = new EntityDao<T, PK>(sessionFactory, entityClass);
        sqlDao = new SqlDao(sessionFactory);
    }


    public EntityDao<T, PK> entityDao() {
        return entityDao;
    }

    public SqlDao sqlDao() {
        return sqlDao;
    }

    public void flush() {
        entityDao().flush();
    }

    public void merge(T entity) {
        entityDao().merge(entity);
    }

    public void clear() {
        entityDao().clear();
    }

    public void evict(T entity) {
        entityDao().evict(entity);
    }

//    public void evict() {
//        entityDao().evict();
//    }

    public T get(PK id) {
        return entityDao().get(id);
    }

    public void refresh(T entity) {
        entityDao().refresh(entity);
    }

    public void save(T entity) {
        entityDao().save(entity);
    }

    public void delete(T entity) {
        entityDao().delete(entity);
    }

    public void delete(PK id) {
        entityDao().delete(id);
    }

    public List<T> findAll() {
        return entityDao().findAll();
    }

    public Page<T> findAll(Page<T> page) {
        return entityDao().findAll(page);
    }

    public List<T> findByQuery(String hql, Object... values) {
        return entityDao().find(hql, values);
    }

    public Page<T> findPageByQuery(Page<T> page, String hql, Object... values) {
        return entityDao().findPage(page, hql, values);
    }

    public T findUnique(String hql, Object... values) {
        return (T) entityDao().findUnique(hql, values);
    }

    public List<T> findByCriteria(Object... criterions) {
        return entityDao().findByCriteria(criterions);
    }

    public Page<T> findPageByCriteria(Page<T> page, Object... criterions) {
        return entityDao().findPageByCriteria(page, criterions);
    }

    public List<T> findByProperty(String propertyName, Object value) {
        return entityDao().findByProperty(propertyName, value);
    }

    public Page<T> findPageByProperty(Page<T> page, String propertyName, Object value) {
        return entityDao().findPageByPropety(page, propertyName, value);
    }

    public T findUniqueByProperty(String propertyName, Object value) {
        return entityDao().findUniqueByProperty(propertyName, value);
    }

    public T findUniqueByCriteria(Object... criterions) {
        return entityDao().findUniqueByCriteria(criterions);
    }

    public Long findLongByQuery(String hql, Object... values) {
        return entityDao().findLong(hql, values);
    }

    public Map findUniqueMapByQuery(String hql, Object... values) {
        return entityDao().findUniqueMapByQuery(hql, values);
    }

    public Map findUniqueBySql(String sql, Object... values) {
        return sqlDao().findUniqueBySql(sql, values);
    }

    public List<Map> findMapByQuery(String hql, Object... values) {
        return entityDao().findMapByQuery(hql, values);
    }

    public List<Map> findBySql(String hql, Object... values) {
        return sqlDao().findBySql(hql, values);
    }

    public Page<Map> findPageBySql(Page<Map> page, String sql, Object... values) {
        return sqlDao().findPage(page, sql, values);
    }
}
