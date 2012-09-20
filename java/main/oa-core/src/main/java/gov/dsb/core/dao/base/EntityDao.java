package gov.dsb.core.dao.base;

import gov.dsb.core.utils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Hibernate的范型基类.
 * <p/>
 * 可以在service类中直接创建使用.也可以继承出DAO子类,在多个Service类中共享DAO操作.
 * 参考Spring2.5自带的Petlinc例子,取消了HibernateTemplate.
 * 通过Hibernate的sessionFactory.getCurrentSession()获得session,直接使用Hibernate原生API.
 *
 * @author calvin
 * @param <T> DAO操作的对象类型
 * @param <PK> 主键类型
 */
//@SuppressWarnings("unchecked")
public class EntityDao<T, PK extends Serializable> implements IDao {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected SessionFactory sessionFactory;

    protected Class<T> entityClass;

    public EntityDao(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void save(T entity) {
        Assert.notNull(entity);
        getSession().saveOrUpdate(entity);
        logger.info("save entity: {}", entity);
    }

    public void merge(T entity) {
        Assert.notNull(entity);
        getSession().merge(entity);
        logger.info("merge entity: {}", entity);
    }

    public void refresh(T entity) {
        Assert.notNull(entity);
        getSession().refresh(entity);
        logger.info("refresh entity: {}", entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity);
        getSession().delete(entity);
        logger.info("delete entity: {}", entity);
    }

    public void delete(PK id) {
        Assert.notNull(id);
        delete(get(id));
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public Page<T> findAll(Page<T> page) {
        return findPageByCriteria(page);
    }

    /**
     * 按id获取对象.
     */
    public T get(final PK id) {
        return (T) getSession().get(entityClass, id);
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param hql    hql语句
     * @param values 数量可变的参数
     */
    public List<T> find(String hql, Object... values) {
        return createQuery(hql, values).list();
    }

    /**
     * 按HQL分页查询.
     * 暂不支持自动获取总结果数,需用户另行执行查询.
     *
     * @param page   分页参数.包括pageSize 和firstResult.
     * @param hql    hql语句.
     * @param values 数量可变的参数.
     * @return 分页查询结果,附带结果列表及所有查询时的参数.
     */
    public Page<T> findPage(Page<T> page, String hql, Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            String lowerHql = hql.trim().toLowerCase();
            int fromPos = 0;
            if (!lowerHql.startsWith("from ")) {
                fromPos = lowerHql.indexOf(" from ") + 1;
            }

            String countHql;
            if (fromPos == 0) {
                countHql = "select count(*) " + hql;
            } else {
                countHql = "select count(*) " + hql.substring(fromPos);
            }
            int totalCount;
            
            //todo 解决使用count查询中，hql有order by语句的查询错误问题 -xiejiao
            if(countHql.contains("order ")){
                countHql = countHql.split("order ")[0]; 
            }
            //
            Query countQuery = createQuery(countHql, values);
            totalCount = ((Long) (countQuery.uniqueResult())).intValue();
            page.setTotalCount(totalCount);
            logger.debug("totalCount=" + totalCount);
//            logger.warn("HQL查询暂不支持自动获取总结果数,hql为{}" + hql);
        }

        Query q = createQuery(hql, values);
        if (page.isFirstSetted()) {
            q.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            q.setMaxResults(page.getPageSize());
        }
        page.setResult(q.list());
        return page;
    }

    /**
     * 按HQL查询唯一对象.
     *
     * @param hql
     * @param values
     * @return
     */
    public Object findUnique(String hql, Object... values) {
        return (createQuery(hql, values).uniqueResult());
    }

    /**
     * 按HQL查询Intger类形结果.
     */
    public Integer findInt(String hql, Object... values) {
        return (Integer) findUnique(hql, values);
    }

    /**
     * 按HQL查询Long类型结果.
     */
    public Long findLong(String hql, Object... values) {
        return (Long) findUnique(hql, values);
    }

    public Map findUniqueMapByQuery(String hql, Object... values) {
        Query query = createQuery(hql, values);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return (Map) query.uniqueResult();
    }

    public List<Map> findMapByQuery(String hql, Object... values) {
        Query query = createQuery(hql, values);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    /**
     * 按Criterion查询对象列表.
     *
     * @param criterions 数量可变的Criterion.
     * @return
     */
    public List<T> findByCriteria(Object... criterions) {
        return createCriteria(criterions).list();
    }

    /**
     * 按Criterion分页查询.
     *
     * @param page       分页参数.包括pageSize、firstResult、orderBy、asc、autoCount.
     *                   其中firstResult可直接指定,也可以指定pageNo.
     *                   autoCount指定是否动态获取总结果数.
     * @param criterions 数量可变的Criterion.
     * @return 分页查询结果.附带结果列表及所有查询时的参数.
     */
    public Page<T> findPageByCriteria(Page page, Object... criterions) {
        Assert.notNull(page);

        Criteria c = createCriteria(criterions);

        if (page.isAutoCount()) {
            page.setTotalCount(countQueryResult(c));
        }
        if (page.isFirstSetted()) {
            c.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            c.setMaxResults(page.getPageSize());
        }

        if (page.isOrderBySetted()) {
            if (page.getOrder().endsWith(QueryParameter.ASC)) {
                c.addOrder(Order.asc(page.getOrderBy()));
            } else {
                c.addOrder(Order.desc(page.getOrderBy()));
            }
        }
        page.setResult(c.list());
        return page;
    }

    /**
     * 按属性查找对象列表.
     */
    public List<T> findByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return createCriteria(Restrictions.eq(propertyName, value)).list();
    }

    /**
     * 按属性查找对象分页
     *
     * @param page
     * @param propertyName
     * @param value
     * @return
     */
    public Page<T> findPageByPropety(Page<T> page, String propertyName, Object value) {
        Assert.notNull(page);
        Assert.hasText(propertyName);

        Criterion criterion = Restrictions.eq(propertyName, value);
        return findPageByCriteria(page, criterion);
    }

    /**
     * 按属性查找唯一对象.
     *
     * @param propertyName
     * @param value
     * @return
     */
    public T findUniqueByProperty(String propertyName, Object value) {
        Assert.hasText(propertyName);
        return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
    }

    /**
     * 按Creterion条件查找唯一对象
     *
     * @param creterions
     * @return
     */
    public T findUniqueByCriteria(Object... creterions) {
        return (T) createCriteria(creterions).uniqueResult();
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    public Query createQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = getSession().createQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject;
    }


    /**
     * 根据Criterion条件创建Criteria,后续可进行更多处理,辅助函数.
     */
    public Criteria createCriteria(Object... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Object c : criterions) {
//            System.out.println("c.getClass().getName() = " + c.getClass().getName());
            if (c instanceof Criterion)
                criteria.add((Criterion) c);
            else if (c instanceof Order)
                criteria.addOrder((Order) c);
        }
        return criteria;
    }

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * <p/>
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原值(orgValue)则不作比较.
     * 传回orgValue的设计侧重于从页面上发出Ajax判断请求的场景.
     * 否则需要SS2里那种以对象ID作为第3个参数的isUnique函数.
     */
    public boolean isPropertyUnique(String propertyName, Object newValue, Object orgValue) {
        if (newValue == null || newValue.equals(orgValue)) {
            return true;
        }

        Object object = findUniqueByProperty(propertyName, newValue);
        return (object == null);
    }

    /**
     * 通过count查询获得本次查询所能获得的对象总数.
     *
     * @return page对象中的totalCount属性将赋值.
     */
    protected int countQueryResult(Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        try {
            orderEntries = (List) BeanUtils.getFieldValue(impl, "orderEntries");
            BeanUtils.setFieldValue(impl, "orderEntries", new ArrayList());
        }
        catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        // 执行Count查询
        int totalCount = (Integer) c.setProjection(Projections.rowCount()).uniqueResult();
        if (totalCount < 1) {
            return 0;
        }

        // 将之前的Projection和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            c.setResultTransformer(transformer);
        }

        try {
            BeanUtils.setFieldValue(impl, "orderEntries", orderEntries);
        }
        catch (Exception e) {
            logger.error("不可能抛出的异常:{}", e.getMessage());
        }

        return totalCount;
    }

    public List<T> findByDetachedCriteria(final DetachedCriteria criteria) {
        Criteria executableCriteria = criteria.getExecutableCriteria(getSession());
        return executableCriteria.list();
    }

    public void flush() {
        getSession().flush();
    }

    public void clear() {
        getSession().clear();
    }

    public void evict(T entity) {
        getSession().evict(entity);
    }

    public void evict() {
        getSessionFactory().evict(entityClass);
    }
}
