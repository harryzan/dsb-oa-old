package gov.dsb.core.dao.base;

import gov.dsb.core.utils.BeanUtils;
import org.hibernate.*;
import org.hibernate.criterion.*;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.sql.SQLException;
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
public class NewEntityDao<T, PK extends Serializable> {

    protected Logger logger = LoggerFactory.getLogger(getClass());

//    protected SessionFactory sessionFactory;
    protected HibernateTemplate hibernateTemplate;

    protected Class<T> entityClass;

    public NewEntityDao(SessionFactory sessionFactory, Class<T> entityClass) {
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
        this.entityClass = entityClass;
    }

    public HibernateTemplate getHibernateTemplate() {
        return this.hibernateTemplate;
    }

//    public SessionFactory getSessionFactory() {
//        return this.hibernateTemplate.getSessionFactory();
//    }

    public void save(T entity) {
        Assert.notNull(entity);
        getHibernateTemplate().saveOrUpdate(entity);
        logger.info("save entity: {}", entity);
    }

    public void merge(T entity) {
        Assert.notNull(entity);
        getHibernateTemplate().merge(entity);
        logger.info("merge entity: {}", entity);
    }

    public void refresh(T entity) {
        Assert.notNull(entity);
        getHibernateTemplate().refresh(entity);
        logger.info("refresh entity: {}", entity);
    }

    public void delete(T entity) {
        Assert.notNull(entity);
        getHibernateTemplate().delete(entity);
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
        return (T) getHibernateTemplate().get(entityClass, id);
    }

    /**
     * 按HQL查询对象列表.
     *
     * @param hql    hql语句
     * @param values 数量可变的参数
     */
    public List<T> find(String hql, Object... values) {
        return getHibernateTemplate().find(hql, values);
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
    public Page<T> findPage(final Page<T> page, String hql, final Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            String lowerHql = hql.trim().toLowerCase();
            int fromPos = 0;
            if (!lowerHql.startsWith("from ")) {
                fromPos = lowerHql.indexOf(" from ") + 1;
            }

            final String countHql;
            if (fromPos == 0) {
                countHql = "select count(*) " + hql.split("order ")[0];
            } else {
                countHql = "select count(*) " + hql.substring(fromPos).split("order ")[0];
            }
            int totalCount;
            
            //todo 解决使用count查询中，hql有order by语句的查询错误问题 -xiejiao
//            if(countHql.contains("order ")){
//                countHql = countHql.split("order ")[0];
//            }
            //
            totalCount = (Integer)getHibernateTemplate().execute(new HibernateCallback<Object>() {
                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    return ((Long)createQuery(session, countHql, values).uniqueResult()).intValue();
                }
            });
//            Query countQuery = createQuery(countHql, values);
//            totalCount = ((Long) (countQuery.uniqueResult())).intValue();
            page.setTotalCount(totalCount);
            logger.debug("totalCount=" + totalCount);
//            logger.warn("HQL查询暂不支持自动获取总结果数,hql为{}" + hql);
        }

        final String fhql = hql;
        List<T> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query q = createQuery(session, fhql, values);
                if (page.isFirstSetted()) {
                    q.setFirstResult(page.getFirst());
                }
                if (page.isPageSizeSetted()) {
                    q.setMaxResults(page.getPageSize());
                }
                return q.list();
            }
        });


        page.setResult(list);
        return page;
    }

    /**
     * 按HQL查询唯一对象.
     *
     * @param hql
     * @param values
     * @return
     */
    public Object findUnique(final String hql, final Object... values) {
        return getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return createQuery(session, hql, values).uniqueResult();
            }
        });
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

    public Map findUniqueMapByQuery(final String hql, final Object... values) {
        return getHibernateTemplate().execute(new HibernateCallback<Map>() {
            @Override
            public Map doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQuery(session, hql, values);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                return (Map) query.uniqueResult();
            }
        });
    }

    public List<Map> findMapByQuery(final String hql, final Object... values) {
        return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = createQuery(session, hql, values);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                return query.list();
            }
        });
    }

    /**
     * 按Criterion查询对象列表.
     *
     * @param criterions 数量可变的Criterion.
     * @return
     */
    public List<T> findByCriteria(Object... criterions) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(entityClass);
        for (Object c : criterions) {
            if (c instanceof Criterion)
                detachedCriteria.add((Criterion) c);
            else if (c instanceof Order)
                detachedCriteria.addOrder((Order) c);
        }
        return getHibernateTemplate().findByCriteria(detachedCriteria);
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
    public Page<T> findPageByCriteria(final Page page, final Object... criterions) {
        Assert.notNull(page);

        List<T> list = getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Criteria c = createCriteria(session, criterions);

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
                return c.list();
            }
        });
        page.setResult(list);
        return page;
    }

    /**
     * 按属性查找对象列表.
     */
    public List<T> findByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        return getHibernateTemplate().executeFind(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return createCriteria(session, Restrictions.eq(propertyName, value)).list();
            }
        });
//        return createCriteria(Restrictions.eq(propertyName, value)).list();
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
    public T findUniqueByProperty(final String propertyName, final Object value) {
        Assert.hasText(propertyName);
        return (T)getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return createCriteria(session, Restrictions.eq(propertyName, value)).uniqueResult();
            }
        });
//        return (T) createCriteria(Restrictions.eq(propertyName, value)).uniqueResult();
    }

    /**
     * 按Creterion条件查找唯一对象
     *
     * @param creterions
     * @return
     */
    public T findUniqueByCriteria(final Object... creterions) {
        return (T)getHibernateTemplate().execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return createCriteria(session, creterions).uniqueResult();
            }
        });
//        return (T) createCriteria(creterions).uniqueResult();
    }

    /**
     * 根据查询函数与参数列表创建Query对象,后续可进行更多处理,辅助函数.
     */
    public Query createQuery(Session session, String queryString, Object... values) {
        Assert.hasText(queryString);
        Query queryObject = session.createQuery(queryString);
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
    public Criteria createCriteria(Session session, Object... criterions) {
        Criteria criteria = session.createCriteria(entityClass);
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

//    public List<T> findByDetachedCriteria(final DetachedCriteria criteria) {
//        Criteria executableCriteria = criteria.getExecutableCriteria(getHibernateTemplate());
//        return executableCriteria.list();
//    }

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }

    public void evict(T entity) {
        getHibernateTemplate().evict(entity);
    }

//    public void evict() {
//        getSessionFactory().evict(entityClass);
//    }
}
