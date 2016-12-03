package com.gpi.scheduling.model;

/**
 * @author Diego Mansilla on 11/24/2016.
 */
//This is not going to be read of course ...
public class SpecificProfessor {
    private String id;
    private Option option;

    public SpecificProfessor(String id, Option option) {
        this.id = id;
        this.option = option;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }
}
