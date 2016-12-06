package com.gpi.scheduling.model;

import java.util.List;

/**
 * @author Diego Mansilla on 11/23/2016.
 */
public class Option {
    String courseId;
    List<Period> periodsTime;

    public Option(String courseId, List<Period> periodsTime) {
        this.courseId = courseId;
        this.periodsTime = periodsTime;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public List<Period> getPeriodsTime() {
        return periodsTime;
    }

    public void setPeriodsTime(List<Period> periodsTime) {
        this.periodsTime = periodsTime;
    }
}
