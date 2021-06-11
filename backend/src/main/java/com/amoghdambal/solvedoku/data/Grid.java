package com.amoghdambal.solvedoku.data;

import org.springframework.data.annotation.Id;

public class Grid {
    @Id
    private String id;

    private String gridRepresentation;

    public Grid() {
        this.gridRepresentation = null;
    }

    public Grid(String grid) {
        this.gridRepresentation = grid;
    }

    public Grid(Grid g) {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newID) {
        this.id = newID;
    }

    public String getGridRepresentation() {
        return this.gridRepresentation;
    }

    public void setGridRepresentation(String newRepr) {
        this.gridRepresentation = newRepr;
    }
}
