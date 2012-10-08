package gov.dsb.web.action;

import gov.dsb.core.dao.DemandTypeDao;
import gov.dsb.core.dao.base.Page;
import gov.dsb.core.domain.DemandType;
import gov.dsb.core.struts2.PageActionSupport;
import gov.dsb.core.utils.Nulls;
import gov.dsb.core.utils.StringHelp;
import gov.dsb.web.ui.grid.Grid;
import gov.dsb.web.ui.grid.QueryTranslate;
import gov.dsb.web.ui.grid.Row;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2009-7-5
 * Time: 10:17:16
 * To change this template use File | Settings | File Templates.
 */

@ParentPackage("default")
@Results({@Result(name = PageActionSupport.GRIDDATA, location = "/WEB-INF/pages/common/gridData.jsp")})
public class LeftAction extends PageActionSupport<DemandType> {

    @Autowired
    private DemandTypeDao service;

    private List<DemandType> types;

    public List<DemandType> getTypes() {
        return types;
    }

    public void setTypes(List<DemandType> types) {
        this.types = types;
    }

    public void prepare() throws Exception {

    }

    public String list() throws Exception {
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        types = service.findAll();

        return super.execute();    //To change body of overridden methods use File | Settings | File Templates.
    }
}