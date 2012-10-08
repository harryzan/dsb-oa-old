package gov.dsb.web.action.oa.car;

import gov.dsb.core.dao.CarDao;
import gov.dsb.core.dao.CarUseDao;
import gov.dsb.core.domain.Car;
import gov.dsb.core.domain.CarUse;
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
@Results({@Result(name = CRUDActionSupport.RELOAD, location = "car-use-grid", type = "chain")})
public class CarUseAction extends CRUDActionSupport<CarUse>{

    @Autowired
    private CarUseDao service;


    @Autowired
    private CarDao  carDao;

    @Autowired
    private UserSessionService userSessionService;

    private String gridParam;

    public void setGridParam(String gridParam) {
        this.gridParam = gridParam;
    }


    private Collection<Car> cars;

    public Collection<Car> getCars() {
        return cars;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }

    protected Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long carid;

    public Long getCarid() {
        return carid;
    }

    public void setCarid(Long carid) {
        this.carid = carid;
    }

    public String save() throws Exception {
//        System.out.println("********************** carid = " + carid);

        Car car = carDao.get(carid);
        entity.setCar(car);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        String day = sdf.format(d);
        entity.setSubmitdate(day);
        entity.setUser(userSessionService.getCurrentSysUser());
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
                entity = new CarUse();
                entity.setStatus(false);
            }
        }


        cars = carDao.findAll();
        System.out.println("cars.size() = " + cars.size());
    }

    public CarUse getModel() {
        return entity;
    }

}
