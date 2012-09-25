package gov.dsb.core.struts2;

import com.opensymphony.xwork2.Preparable;

/**
 * Struts2中List典型Action规范类.
 * 规范了一些函数的命名.
 *
 * @author harry
 * @param <T> CRUD所管理的对象类型
 */
@SuppressWarnings("serial")
public abstract class TreeActionSupport<T> extends SimpleActionSupport implements Preparable {

    public static final String TREEDATA = "treedata";    

    /**
     * Action函数,显示Entity列表.
     * return SUCCESS.
     */
    public abstract String treedata() throws Exception;
}