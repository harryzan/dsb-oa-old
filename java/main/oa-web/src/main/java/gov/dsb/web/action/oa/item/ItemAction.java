package gov.dsb.web.action.oa.item;

import gov.dsb.core.dao.CarDao;
import gov.dsb.core.dao.ItemDao;
import gov.dsb.core.domain.Car;
import gov.dsb.core.domain.Item;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-15
 * Time: 10:57:24
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "item-grid", type = "chain")})
public class ItemAction extends CRUDActionSupport<Item>{

    @Autowired
    private ItemDao service;

    private String gridParam;

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }

    protected Long id;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String save() throws Exception {
        entity.setItemcount(entity.getItemcount() + count);
        service.save(entity);
        return RELOAD;
    }

    public String delete() throws Exception {
        service.delete(id);
        return RELOAD;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new Item();
                entity.setItemcount(0);
            }
        }
    }

    public Item getModel() {
        return entity;
    }

}
