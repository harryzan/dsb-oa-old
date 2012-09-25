package gov.dsb.core.struts2;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
 * Struts2中CRUD典型Action规范类.
 * 规定使用Preparable,ModelDriven接口,规范了一些函数的命名.
 *
 * @author calvin
 * @param <T> CRUD所管理的对象类型
 */
@SuppressWarnings("serial")
public abstract class CRUDActionSupport<T> extends SimpleActionSupport implements ModelDriven<T>, Preparable {

    protected T entity;

    /**
     * 进行CUD操作后,以redirect方式重新打开action默认页的result名.
     */
    public static final String RELOAD = "reload";

    public static final String DELETE = "delete";

    /**
     * 默认VIEW操作
     */
    public static final String VIEW = "view";

    /**
     * Action函数,默认action函数，默认指向view函数.
     */
    @Override
    public String execute() throws Exception {
        return VIEW;
    }

    /**
     * Action函数,新增或修改Entity.
     * return RELOAD.
     */
    public abstract String save() throws Exception;

    /**
     * Action函数,删除Entity.
     * return RELOAD.
     */
    public abstract String delete() throws Exception;

    /**
     * 在save()前执行二次绑定.
     */
    public void prepareSave() throws Exception {
        prepareModel();
    }

    /**
     * 在input()前执行二次绑定.
     */
    public void prepareInput() throws Exception {
        prepareModel();
    }

    /**
     * 公共的二次绑定(默认VIEW操作)
     */
    public void prepare() throws Exception {
        prepareModel();
    }

    /**
     * 等同于prepare()的内部函数.
     */
    protected abstract void prepareModel() throws Exception;
}
