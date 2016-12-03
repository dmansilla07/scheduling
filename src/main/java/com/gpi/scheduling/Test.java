package com.gpi.scheduling;

import com.gpi.scheduling.model.*;
import com.gpi.scheduling.service.ScheduleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Diego Mansilla on 12/3/2016.
 */
public class Test {
    public static void main(String[] args) {
        ScheduleService scheduleService = new ScheduleService();
        int semester = 1;
        List<Course> courseList = new ArrayList<>();
        List<Student> studentList = new ArrayList<>();
        List<Professor> professorList = new ArrayList<>();

        List<Period> periodList1 = new ArrayList<>();
        List<Period> periodList2 = new ArrayList<>();

        List<Option> optionList = new ArrayList<>();

        Course course1 = new Course("1", 5, 1, 1, 40);

        periodList1.add(new Period(0, 2));
        periodList2.add(new Period(1, 3));

        optionList.add(new Option(course1, periodList1));
        optionList.add(new Option(course1, periodList2));

        professorList.add(new Professor("1", ));

        courseList.add(new Course("1", 5, 1, 1, 40));
        courseList.add(new Course("2", 5, 1, 1, 40));
        courseList.add(new Course("3", 5, 1, 1, 40));

    }
}
