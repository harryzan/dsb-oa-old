package gov.dsb.web.action.oa.car;

import gov.dsb.core.dao.CarDao;
import gov.dsb.core.dao.base.Page;
import gov.dsb.core.domain.Car;
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
public class CarGridAction extends PageActionSupport<Car> {

    @Autowired
    private CarDao service;

    private String columns;

    private Integer start;

    private Integer limit;

    private String conditions;

    private List<Row> rows;

    private String gridParams;

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public List<Row> getRows() {
        return rows;
    }

    public String getGridParams() {
        return gridParams;
    }

    public void setGridParams(String gridParams) {
        this.gridParams = gridParams;
    }


    public void prepare() throws Exception {
        if (gridParams == null) {
            gridParams = "";
        }
    }

    public String list() throws Exception {
        return SUCCESS;
    }

    public String griddata() throws Exception {
        if (!Nulls.isNull(limit) && limit > 0) {
            page = new Page<Car>(limit, true);
        }
        else {
            page = new Page<Car>(10, true);
        }

        if (!Nulls.isNull(start) && !Nulls.isNull(limit)) {
            int pageNo = start / limit + 1;
            page.setPageNo(pageNo);
        }
        if (!StringHelp.isEmpty(conditions)) {
            QueryTranslate queryTranslate = new QueryTranslate("from Car", conditions);
            page = service.findPageByQuery(page, queryTranslate.toString());
        }
        else {
            page = service.findAll(page);
        }
        List<Car> list = page.getResult();
        rows = Grid.gridValue2Rows(list, columns);

        return GRIDDATA;
    }
}