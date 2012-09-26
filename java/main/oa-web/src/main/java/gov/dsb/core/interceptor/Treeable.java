package gov.dsb.core.interceptor;

import java.io.Serializable;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: harry
 * Date: 2009-2-2
 * Time: 14:22:37
 * To change this template use File | Settings | File Templates.
 */
public interface Treeable<T> extends Serializable {

    public Long getId();

    public T getParent();

    public Collection<T> getChildren();

    public Boolean getLeaf();

    public void setLeaf(Boolean leaf);

    public String getTreeid();

    public void setTreeid(String treeid);
}
