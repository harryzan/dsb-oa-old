package gov.dsb.web.action.system.sysdept;

import gov.dsb.core.dao.SysDeptDao;
import gov.dsb.core.domain.SysDept;
import gov.dsb.core.struts2.CRUDActionSupport;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-3
 * Time: 11:06:08
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "sys-dept", type = "chain"),
     @Result(name = CRUDActionSupport.DELETE, location = "/common/blank", type = "redirect")})
public class SysDeptAction extends CRUDActionSupport<SysDept> {

    @Autowired
    private SysDeptDao service;

    protected Long id;

    private Long parentid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String save() throws Exception {
        System.out.println("parentid = " + parentid);
        SysDept parent = null;
        if(parentid != null){
            parent = service.get(parentid);
            entity.setParent(parent);
        }
        if(entity.getType() == null){
            entity.setType(false);
        }
        service.save(entity);
        return RELOAD;
    }

    public String delete() throws Exception {
        service.delete(id);
        return DELETE;
    }

    protected void prepareModel() throws Exception {
        if (entity == null) {
            if (id != null) {
                entity = service.get(id);
            }
            else {
                entity = new SysDept();
            }
        }
    }

    public SysDept getModel() {
        return entity;
    }

    public String blank() throws Exception {
        return "blank";
    }

    private Boolean upoperation;

    public Boolean getUpoperation() {
        return upoperation;
    }

    public void setUpoperation(Boolean upoperation) {
        this.upoperation = upoperation;
    }

    /**
     * 上移 / 下移
     * @return                    SUCCESS
     * @throws Exception          .
     */
    public String operation() throws Exception{

        if(entity != null){
            long orderno = -1;
            Collection<SysDept> col = null;
            SysDept temp = null;

            if(entity.getParent() == null){
                col = service.findByQuery("from SysDept where parent is null");
            }
            else {
                col = entity.getParent().getChildren();
            }

            for(SysDept s : col){
                if(upoperation){
                    if(s.getOrderno() < entity.getOrderno() && s.getOrderno() > orderno){
                        temp = s;
                        orderno = s.getOrderno();
                    }
                }
                else{
                    if(s.getOrderno() > entity.getOrderno()){
                        if(orderno == -1){
                            temp = s;
                            orderno = s.getOrderno() - entity.getOrderno();
                        }
                        else if(s.getOrderno() - entity.getOrderno() > orderno){
                            temp = s;
                            orderno = s.getOrderno() - entity.getOrderno();
                        }
                    }
                }
            }

            if(temp != null){
                long no1 = entity.getOrderno();
                long no2 = temp.getOrderno();
                entity.setOrderno(no2);
                temp.setOrderno(no1);

                service.save(entity);
                service.save(temp);
            }
        }


        return SUCCESS;
    }
}
