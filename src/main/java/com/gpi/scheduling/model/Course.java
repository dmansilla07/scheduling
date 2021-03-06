package com.gpi.scheduling.model;

/**
 * @author Diego Mansilla on 11/21/2016.
 */
public class Course {
    private String id;
    private int credits;
    private int semester;
    //I2 I1 or both??
    private int type;

    //This is not going to be read...
    private int numberStudents;

    public Course(String id, int credits, int semester, int type, int numberStudents) {
        this.id = id;
        this.credits = credits;
        this.semester = semester;
        this.type = type;
        this.numberStudents = numberStudents;
    }

    public int getNumberStudents() {
        return numberStudents;
    }

    public void setNumberStudents(int numberStudents) {
        this.numberStudents = numberStudents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
