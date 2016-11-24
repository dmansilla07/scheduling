package com.gpi.scheduling.model;

import java.util.List;

/**
 * @author Diego Mansilla on 11/23/2016.
 */
public class Option {
    Course course;
    List<Period> periodsTime;

    public List<Period> getPeriodsTime() {
        return periodsTime;
    }

    public void setPeriodsTime(List<Period> periodsTime) {
        this.periodsTime = periodsTime;
    }
}
