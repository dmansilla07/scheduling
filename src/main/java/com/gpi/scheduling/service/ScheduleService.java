package com.gpi.scheduling.service;

import com.gpi.scheduling.model.Course;
import com.gpi.scheduling.model.Option;
import com.gpi.scheduling.model.Professor;
import com.gpi.scheduling.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

    public static void getProfessors(List<Course> courses, List<Professor> professorList) {
        int n = courses.size();

    }

    public static List<Course> setSemesterCourse(int semester, List<Course> courseList) {
        List<Course> courses = new ArrayList<Course>();
        for(Course course : courseList) {
            if (course.getSemester() = semester) {
                courses.add(course);
            }
        }
        return courses;
    }

    public static List<Professor> getProfessorForCourses(List<Course> courseList, List<Professor> professorList) {
        List<Professor> professors = new ArrayList<Professor>();
        for(Professor professor : professorList) {
            boolean ok = false;
            for(Course course : courseList) {
                if (ok == true) {
                    break;
                }
                for (Option option : professor.getOptions()) {
                    if (option.getCourse().getId() == course.getId()) {
                        ok = true;
                        break;
                    }
                }
            }
            if (ok == true) {
                professors.add(professor);
            }
        }
        return professors;
    }

}
