package gov.dsb.core.struts2;

import com.opensymphony.xwork2.Preparable;
import gov.dsb.core.dao.base.Page;

/**
 * Struts2中List典型Action规范类.
 * 规范了一些函数的命名.
 *
 * @author harry
 * @param <T> CRUD所管理的对象类型
 */
@SuppressWarnings("serial")
public abstract class PageActionSupport<T> extends SimpleActionSupport implements Preparable {

    public static final String GRIDDATA = "griddata";    

    protected Page<T> page;

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }

    /**
     * Action函数,默认action函数，默认指向list函数.
     */
    @Override
    public String execute() throws Exception {
        return list();
    }

    /**
     * Action函数,显示Entity列表.
     * return SUCCESS.
     */
    public abstract String list() throws Exception;
}
