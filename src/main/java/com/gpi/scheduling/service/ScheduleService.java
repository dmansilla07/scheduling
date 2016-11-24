package com.gpi.scheduling.service;

import com.gpi.scheduling.model.Course;
import com.gpi.scheduling.model.Student;

import java.util.List;

/**
 * @author Diego Mansilla on 11/23/2016.
 */
public class ScheduleService {

    public static void fillCourses(List<Course> courses, List<Student> studentList) {
        //This can be improved using Machine Learning techniques
        for(Student student : studentList) {
            for (Course studentCourse : student.getAvailableCourses()) {
                for (Course course : courses) {
                    if (studentCourse.getId() == course.getId()) {
                        course.setNumberStudents(course.getNumberStudents() + 1);
                        break;
                    }
                }
            }
        }
    }


}
