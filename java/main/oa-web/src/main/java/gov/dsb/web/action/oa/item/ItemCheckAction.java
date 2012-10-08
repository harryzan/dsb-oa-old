package gov.dsb.web.action.oa.item;

import gov.dsb.core.dao.ItemDao;
import gov.dsb.core.dao.ItemUseDao;
import gov.dsb.core.domain.Item;
import gov.dsb.core.domain.ItemUse;
import gov.dsb.core.struts2.CRUDActionSupport;
import gov.dsb.web.security.UserSessionService;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-5-15
 * Time: 10:57:24
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "item-check-grid", type = "chain")})
public class ItemCheckAction extends CRUDActionSupport<ItemUse>{

    @Autowired
    private ItemUseDao service;


    @Autowired
    private ItemDao  itemDao;

    @Autowired
    private UserSessionService userSessionService;

    private String gridParam;

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }


    private Collection<Item> items;

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }

    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long itemid;

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String save() throws Exception {
//        System.out.println("********************** itemid = " + itemid);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String day = sdf.format(d);
        entity.setCheckdate(day);
        entity.setChecker(userSessionService.getCurrentSysUser());
        entity.setStatus(true);
        service.save(entity);

        Item item = entity.getItem();
        item.setItemcount(item.getItemcount() - entity.getUsecount());
        itemDao.save(item);

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
                entity = new ItemUse();
                entity.setStatus(false);
            }
        }


        items = itemDao.findAll();
    }

    public ItemUse getModel() {
        return entity;
    }

}
