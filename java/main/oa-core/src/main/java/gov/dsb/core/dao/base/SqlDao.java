package gov.dsb.core.dao.base;

import org.hibernate.*;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-8-10
 * Time: 16:20:05
 * To change this template use File | Settings | File Templates.
 */
public class SqlDao implements IDao {

    protected SessionFactory sessionFactory;

    public SqlDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Query createSqlQuery(String queryString, Object... values) {
        Assert.hasText(queryString);
        SQLQuery query = getSession().createSQLQuery(queryString);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    public Map findUniqueBySql(String sql, Object... values) {
        Query query = createSqlQuery(sql, values);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return (Map) query.uniqueResult();
    }

    public List<Map> findBySql(String sql, Object... values) {
        Query query = createSqlQuery(sql, values);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }

    public Page<Map> findPage(Page<Map> page, String hql, Object... values) {
        Assert.notNull(page);

        if (page.isAutoCount()) {
            String lowerHql = hql.trim().toLowerCase();
//            int fromPos = 0;
//            if (!lowerHql.startsWith("from ")) {
//                fromPos = lowerHql.indexOf(" from ") + 1;
//            }

            String countHql;
//            if (fromPos == 0) {
//                countHql = "select count(*) " + hql;
//            } else {
//                countHql = "select count(*) " + hql.substring(fromPos);
//            }
            int totalCount;

            //todo 解决使用count查询中，hql有order by语句的查询错误问题 -xiejiao
//            if(countHql.contains("order ")){
//                countHql = countHql.split("order ")[0];
//            }
            //
            countHql = "select count(0) from (" + hql + ")";
//            System.out.println("countHql = " + countHql);

            long t1 = System.currentTimeMillis();
            Query countQuery = createSqlQuery(countHql, values);
            totalCount = ((BigDecimal) (countQuery.uniqueResult())).intValue();
            long t2 = System.currentTimeMillis();
            System.out.println("select count time = " + (t2 - t1) + "ms");

            page.setTotalCount(totalCount);
//            logger.debug("totalCount=" + totalCount);
//            logger.warn("HQL查询暂不支持自动获取总结果数,hql为{}" + hql);
        }

//        hql = "select * from ( " +
//                "select row_.*, ROWNUM rownum_ from (" + hql + ") row_ " +
//                "where ROWNUM <" + page.getFirst() + "+" + page.getPageSize() +
//                ") WHERE rownum_ >= " + page.getFirst();
//        System.out.println("hql = " + hql);

        long t1 = System.currentTimeMillis();
        Query q = createSqlQuery(hql, values);
        q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        if (page.isFirstSetted()) {
            q.setFirstResult(page.getFirst());
        }
        if (page.isPageSizeSetted()) {
            q.setMaxResults(page.getPageSize());
        }
        page.setResult(q.list());
        long t2 = System.currentTimeMillis();
        System.out.println("select result time = " + (t2 - t1) + "ms");
        return page;
    }
}
