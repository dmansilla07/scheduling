package com.gpi.scheduling.model;

import java.util.List;

/**
 * @author Diego Mansilla on 11/21/2016.
 */
public class Student {
    private String id;
    private List<Course> availableCourses;

    public Student(String id, List<Course> availableCourses) {
        this.id = id;
        this.availableCourses = availableCourses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Course> getAvailableCourses() {
        return availableCourses;
    }

    public void setAvailableCourses(List<Course> availableCourses) {
        this.availableCourses = availableCourses;
    }
}
