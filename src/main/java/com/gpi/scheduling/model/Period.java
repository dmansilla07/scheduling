package com.gpi.scheduling.model;

/**
 * @author Diego Mansilla on 11/23/2016.
 */
public class Period {
    private int startHour;
    private int endHour;

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }
}
