package com.gpi.scheduling.model;

import java.util.List;

/**
 * @author Diego Mansilla on 11/21/2016.
 */
public class Professor {
    private String id;
    private List<Option> options;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
