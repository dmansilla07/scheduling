package com.gpi.scheduling.service;

import com.gpi.scheduling.model.Course;
import com.gpi.scheduling.model.Option;
import com.gpi.scheduling.model.Period;
import com.gpi.scheduling.model.SpecificProfessor;
import com.gpi.scheduling.util.Constant;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Diego Mansilla on 11/24/2016.
 */

@Service
public class GeneticService {

    public static int getOverlapFromPeriods(Period period1, Period period2) {

        if (period2.getEndHour() >= period1.getStartHour()) {
            if (period1.getEndHour() >= period2.getEndHour()) {
                return period2.getEndHour() - period1.getStartHour();
            } else {
                return period1.getEndHour() - period1.getStartHour();
            }
        }

        return 0;
    }

    public static int getOverlapping(Option option1, Option option2) {
        int totalOverlapping = 0;
        for(Period period1 : option1.getPeriodsTime()) {
            for (Period period2 : option2.getPeriodsTime()) {
                if (period1.getStartHour() >= period2.getStartHour()) {
                    totalOverlapping += getOverlapFromPeriods(period1, period2);
                } else {
                    totalOverlapping += getOverlapFromPeriods(period2, period1);
                }

                /*if(option1.getCourseId().equals("1") && option2.getCourseId().equals("5") && totalOverlapping > 0 ) {
                    if (period1.getStartHour() >= period2.getStartHour()) {
                        System.out.println(getOverlapFromPeriods(period1, period2));
                    } else {
                        System.out.println(getOverlapFromPeriods(period2, period1));
                    }
                    System.out.println(period1.getStartHour() + " " + period1.getEndHour());
                    System.out.println(period2.getStartHour() + " " + period2.getEndHour());
                }*/
            }
        }
        return totalOverlapping;
    }

    public static double getFitness(List<SpecificProfessor> professorList, List<Course> courseList) {
        double fitness = 0.0;
        for (int i = 0; i < professorList.size(); ++i) {
            SpecificProfessor professor = professorList.get(i);
            for (Course course : courseList) {
                if (!course.getId().equals(professor.getOption().getCourseId())) {
                    int minOverlapping = 1000000;
                    for (int j = 0; j < professorList.size(); ++j) {
                        SpecificProfessor professor2 = professorList.get(j);
                        if (professor2.getOption().getCourseId().equals(course.getId())) {
                            if (professor2.getId().equals(professor.getId())) {
                                if (getOverlapping(professor.getOption(), professor2.getOption()) > 0) {
                                    return Constant.MAX_FITNESS;
                                }
                            } else {
                                minOverlapping = Math.min(minOverlapping, getOverlapping(professor.getOption(), professor2.getOption()));
                            }
                        }
                    }
                    fitness += minOverlapping*Constant.FITNESS_OVERLAP_PENALTY;
                }
            }
        }
        return fitness;
    }
}
