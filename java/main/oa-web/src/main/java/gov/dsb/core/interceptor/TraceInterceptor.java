package gov.dsb.core.interceptor;

import gov.dsb.core.utils.BeanUtils;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * UserSession: Administrator
 * Date: 2007-10-1
 * Time: 11:42:37
 * To change this template use File | Settings | File Templates.
 */
public class TraceInterceptor extends EmptyInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Override
//    public int[] findDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
//                           String[] propertyNames, Type[] types) {
//        if (entity instanceof Treeable) {
//            for (int i = 0; i < propertyNames.length; i++) {
//                if ("leaf".equalsIgnoreCase(propertyNames[i])) {
//                    int size = getChildrenSize((Treeable) entity);
//                    Boolean leaf = ((Treeable) entity).getLeaf();
//                    if ((size == 0 && !leaf) || (size > 0 && leaf)) {
//                        return new int[]{i};
//                    }
//                }
//            }
//        }
//        return null;
//    }

//    @Override
//    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
//                                String[] propertyNames, Type[] types) {
//        boolean ret = false;
//        if (entity instanceof Treeable) {
//            for (int i = 0; i < propertyNames.length; i++) {
//                if ("leaf".equalsIgnoreCase(propertyNames[i])) {
//                    currentState[i] = getChildrenSize((Treeable) entity) == 0;
//                    ret = true;
//                }
//            }
//        }
//        return ret;
//    }



    @Override
    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {
        boolean ret = false;
        if (entity instanceof Treeable) {
            for (int i = 0; i < propertyNames.length; i++) {
                if ("orderno".equalsIgnoreCase(propertyNames[i])) {
                    if (state[i] == null) {
                        state[i] = id;
                        ret = true;
                    }
                }
                if ("treeid".equalsIgnoreCase(propertyNames[i])) {
                    if (state[i] == null) {
                        state[i] = getParentTreeId((Treeable) entity) + id + "/";
                        ret = true;
                    }
                }
                if ("leaf".equalsIgnoreCase(propertyNames[i])) {
                    if (state[i] == null) {
                        state[i] = true;
                        ret = true;
                    }
                }

                Treeable parent = (Treeable) ((Treeable) entity).getParent();
                if (parent != null) {
                    parent.setLeaf(false);
                }
            }
        }
        return ret;
    }

    /**
     * 对于持久化的对象，修改其属性不用显式的调用save方法，hibernate会在session关闭的时候将缓存中的数据同步到数据库中
     * 该方法处理所有实现Treeable接口的实体
     * hibernate在删除实体之前会调用该方法
     *
     * @param entity        待删除实体
     * @param id
     * @param state
     * @param propertyNames
     * @param types
     */
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Treeable) {
            Treeable obj = (Treeable) entity;
            //获得待删除实体的父节点
            Treeable parent = (Treeable) obj.getParent();

            //如果待删除实体有父节点，那么查找出该父节点下的所有子节点
            if (parent != null) {
//                DetachedCriteria criteria = DetachedCriteria.forClass(entity.getClass());
//                criteria.add(Restrictions.eq("parent", parent));
//                Criteria executableCriteria = criteria.getExecutableCriteria(sessionFactory.getCurrentSession());
                Collection list = parent.getChildren();
                logger.debug("****** parent's children size:" + list.size());

                //此时，待删除实体并没有从数据库中删除，所以，该待删除实体的父节点下至少会有一个子节点，那就是该待删除实体,即如果待删除实体存在父节点，那么此时父节点的isLeaf的值为false
                boolean isleaf = list.size() == 0;

                //如果待删除实体的父节点存在，且该父节点下只有待删除实体这一个子节点，那么将待删除实体的父节点设置为叶子节点
                if (list.size() == 1) {
                    if (list.iterator().next().equals(entity)) {
                        isleaf = true;
                    }
                }
                if (isleaf) {
                    parent.setLeaf(true);
                }
            }
        }

        super.onDelete(entity, id, state, propertyNames, types);
    }

    private String getParentTreeId(Treeable entity) {
        String treeId = "";
        Object parent;
        parent = entity.getParent();
        if (parent != null) {
            Serializable tree = getProperty(parent, "treeid");
            if (tree != null) {
                treeId = tree.toString();
            }
            else {
                treeId = getParentTreeId((Treeable) parent) + getProperty(entity, "id") + "/";
            }
        }
        return treeId;
    }

    private int getChildrenSize(Treeable parent) {
        Collection children = parent.getChildren();
        if (children == null) {
            return 0;
        }
        else {
            return children.size();
        }
    }

    private Serializable getProperty(Object entity, String property) {
        Serializable parentid = null;
        try {
            parentid = (Serializable) BeanUtils.getFieldValue(entity, property);
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return parentid;
    }
}
