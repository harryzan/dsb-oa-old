package gov.dsb.web.ui.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * @author You yiming
 */
public class Row {

    private String id;

    private List<Cell> cells = new ArrayList<Cell>();

    public List<Cell> getCells() {
        return this.cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public void addCell(Cell cell) {

        this.cells.add(cell);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
