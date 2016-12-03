package com.gpi.scheduling.model;

import java.util.List;

/**
 * @author Diego Mansilla on 11/23/2016.
 */
public class Option {
    Course course;
    List<Period> periodsTime;

    public Option(Course course, List<Period> periodsTime) {
        this.course = course;
        this.periodsTime = periodsTime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Period> getPeriodsTime() {
        return periodsTime;
    }

    public void setPeriodsTime(List<Period> periodsTime) {
        this.periodsTime = periodsTime;
    }
}
