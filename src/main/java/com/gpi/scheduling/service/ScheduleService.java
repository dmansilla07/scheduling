package com.gpi.scheduling.service;

import com.gpi.scheduling.model.*;
import com.gpi.scheduling.util.Constant;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Diego Mansilla on 11/23/2016.
 */

@Service
public class ScheduleService {

    public static void fillCourses(List<Course> courses, List<Student> studentList) {
        //This can be improved using Machine Learning techniques
        for(Student student : studentList) {
            for (Course studentCourse : student.getAvailableCourses()) {
                for (Course course : courses) {
                    if (studentCourse.getId().equals(course.getId())) {
                        course.setNumberStudents(course.getNumberStudents() + 1);
                        break;
                    }
                }
            }
        }
    }

    //All course List
    public static List<Course> getSemesterCourse(int semester, List<Course> courseList) {
        List<Course> courses = new ArrayList<Course>();
        for(Course course : courseList) {
            if (course.getSemester() == semester) {
                courses.add(course);
            }
        }
        return courses;
    }

    //Especific Course List, General Professor List
    public static List<SpecificProfessor> getProfessorForCourses(List<Course> courseList, List<Professor> professorList) {
        List<SpecificProfessor> professors = new ArrayList<SpecificProfessor>();
        for(Professor professor : professorList) {
            boolean ok = false;
            for(Course course : courseList) {
                for (Option option : professor.getOptions()) {

                    //System.out.println(option.getCourseId() + " " + course.getId());
                    if (option.getCourseId().equals(course.getId())) {
                        SpecificProfessor specificProfessor = new SpecificProfessor(professor.getId(), option);
                        specificProfessor.setId(professor.getId());
                        specificProfessor.setOption(option);
                        professors.add(specificProfessor);
                    }
                }
            }
        }
        return professors;
    }

    public static void printGeneration(List<SpecificProfessor> generationProfessor) {
        for(SpecificProfessor specificProfessor : generationProfessor) {
            System.out.println(specificProfessor.getOption().getCourseId() + " " + specificProfessor.getId());
        }
    }

    //General Course and Professor List
    public List<SpecificProfessor> getProfessorOptionsForSemester(int semester, List<Course> courseList,
                                                                 List<Professor> professorList, List<Student> studentList) {
        fillCourses(courseList, studentList);
        List<Course> semesterCourses = getSemesterCourse(semester, courseList);
        List<SpecificProfessor> semesterProfessors = getProfessorForCourses(semesterCourses, professorList);

        List<SpecificProfessor> semesterFinalProfessors = new ArrayList<SpecificProfessor>();
        double bestFitness = Constant.MAX_FITNESS;

        for(int generation = 1; generation < Constant.MAX_GENERATION; ++generation) {
            //More or less 7*10, can be optimized
            int n = semesterCourses.size();
            List<SpecificProfessor> nextGenerationProfessors = new ArrayList<SpecificProfessor>();
            for (Course course : semesterCourses) {
                List<SpecificProfessor> professorOptionForCourse = new ArrayList<SpecificProfessor>();
                for (SpecificProfessor professor : semesterProfessors) {
                    if (professor.getOption().getCourseId().equals(course.getId())) {
                        professorOptionForCourse.add(professor);
                    }
                }
                List<Integer> Idxs = new ArrayList<Integer>();
                int nPr = professorOptionForCourse.size();
                long seed = System.nanoTime();
                for (int i=0; i<nPr; ++i) {
                    Idxs.add(i);
                }
                Collections.shuffle(Idxs, new Random(seed));
                for(int i=0; i<course.getNumberStudents()/Constant.REGULAR_STUDENTS_PER_CLASS && i < Idxs.size(); ++i) {
                    nextGenerationProfessors.add(professorOptionForCourse.get(Idxs.get(i)));
                }
            }

            if (generation == 1) {
                bestFitness = GeneticService.getFitness(nextGenerationProfessors, semesterCourses);
                semesterFinalProfessors = nextGenerationProfessors;
            } else {
                List<SpecificProfessor> gen1 = new ArrayList<SpecificProfessor>();
                List<SpecificProfessor> gen2 = new ArrayList<SpecificProfessor>();
                Random rand = new Random(System.nanoTime());
                long nn = nextGenerationProfessors.size();
                int random_mask = rand.nextInt((1<<nn));
                for(int i=0; i<nn; ++i) {
                    if ( ((random_mask>>i) & 1) == 1 ) {
                        gen1.add(semesterFinalProfessors.get(i));
                        gen2.add(nextGenerationProfessors.get(i));
                    } else {
                        gen2.add(semesterFinalProfessors.get(i));
                        gen1.add(nextGenerationProfessors.get(i));
                    }
                }
                if (generation % 10000 == 0) {
                    System.out.println("Generation " + generation + " Fitness : " + bestFitness);
                }
                double ChildFitness1 = GeneticService.getFitness(gen1, semesterCourses);
                double ChildFitness2 = GeneticService.getFitness(gen2, semesterCourses);
                if (ChildFitness1 < bestFitness) {
                    semesterFinalProfessors = gen1;
                    bestFitness = ChildFitness1;
                }
                if (ChildFitness2 < bestFitness) {
                    semesterFinalProfessors = gen2;
                    bestFitness = ChildFitness2;
                }


            }
            if (generation <= 10 || generation % 10000 == 0) {
                System.out.println("Generation " + generation + " Fitness : " + bestFitness);

                ///printGeneration(semesterFinalProfessors);
            }
        }
        return semesterFinalProfessors;
    }

}
