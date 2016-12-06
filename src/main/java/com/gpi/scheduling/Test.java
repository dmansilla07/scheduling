package com.gpi.scheduling;

import com.gpi.scheduling.model.*;
import com.gpi.scheduling.service.ScheduleService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalLong;
import java.util.Scanner;

/**
 * @author Diego Mansilla on 12/3/2016.
 */
public class Test {
    public static List<Course> getCourse(File file) {
        List<Course> courseList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String id = scanner.next();
                int cred = scanner.nextInt();
                int semester = scanner.nextInt();
                int type = scanner.nextInt();
                courseList.add(new Course(id, cred, semester, type, 20));
            }
            return courseList;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static List<Professor> getProfessors(File file) {
        List<Professor> professorList= new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String idProfessor = scanner.next();
                int num_options = scanner.nextInt();
                List<Option> optionList = new ArrayList<>();
                for(int i=0; i<num_options; ++i) {
                    String id_course = scanner.next();
                    int num_periods = scanner.nextInt();
                    List<Period> periodList = new ArrayList<>();
                    for(int j=0; j<num_periods; ++j) {
                        int st = scanner.nextInt();
                        int en = scanner.nextInt();
                        periodList.add(new Period(st, en));
                    }
                    optionList.add(new Option(id_course, periodList));
                }
                professorList.add(new Professor(idProfessor, optionList));
            }
            return professorList;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        ScheduleService scheduleService = new ScheduleService();
        int semester = 1;
        List<Course> courseList = getCourse(new File("C:\\Users\\dmans\\Desktop\\ProyectoGPI\\Courses.txt"));
        List<Student> studentList = new ArrayList<>();
        List<Professor> professorList = getProfessors(new File("C:\\Users\\dmans\\Desktop\\ProyectoGPI\\Professor.txt"));

        List<Period> periodList1 = new ArrayList<>();
        List<Period> periodList2 = new ArrayList<>();

        List<Option> optionList = new ArrayList<>();

        Course course1 = new Course("1", 5, 1, 1, 40);

        periodList1.add(new Period(0, 2));
        periodList2.add(new Period(1, 3));

        optionList.add(new Option(course1, periodList1));
        optionList.add(new Option(course1, periodList2));

        professorList.add(new Professor("1", optionList));

        courseList.add(new Course("1", 5, 1, 1, 40));
        courseList.add(new Course("2", 5, 1, 1, 40));
        courseList.add(new Course("3", 5, 1, 1, 40));

        List<SpecificProfessor> specificProfessorList = new ArrayList<>();
        specificProfessorList = scheduleService.getProfessorOptionsForSemester(1, courseList, professorList, studentList);
        for (SpecificProfessor specificProfessor : specificProfessorList) {
            System.out.println(specificProfessor.getId());
        }

    }
}
