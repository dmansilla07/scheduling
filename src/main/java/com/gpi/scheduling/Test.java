package com.gpi.scheduling;

import com.gpi.scheduling.model.*;
import com.gpi.scheduling.service.GeneticService;
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
                courseList.add(new Course(id, cred, semester, type, 40));
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

        List<SpecificProfessor> specificProfessorList = new ArrayList<>();
        specificProfessorList = scheduleService.getProfessorOptionsForSemester(1, courseList, professorList, studentList);

        List<Course> semesterCourses = ScheduleService.getSemesterCourse(1, courseList);

        GeneticService geneticService = new GeneticService();

        System.out.println("Fitness: " + GeneticService.getFitness(specificProfessorList, semesterCourses));
        for (SpecificProfessor specificProfessor : specificProfessorList) {
            System.out.println("Professor : " +specificProfessor.getId());
                Option option = specificProfessor.getOption();
                System.out.println("Curso: " + option.getCourseId());
                for(Period period : option.getPeriodsTime()) {
                    System.out.println(period.getStartHour() + " " + period.getEndHour());
                }

        }

    }
}
