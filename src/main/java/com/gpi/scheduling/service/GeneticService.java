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
        if (period1.getEndHour() > period2.getStartHour() && period2.getEndHour() > period1.getEndHour()) {
            if (period1.getStartHour() >= period2.getStartHour()) {
                return period1.getEndHour() - period1.getStartHour();
            } else {
                return period1.getEndHour() - period2.getStartHour();
            }
        }
        return 0;
    }

    public static int getOverlapping(Option option1, Option option2) {
        int totalOverlapping = 0;
        for(Period period1 : option1.getPeriodsTime()) {
            for (Period period2 : option2.getPeriodsTime()) {
                totalOverlapping += getOverlapFromPeriods(period1, period2);
                totalOverlapping += getOverlapFromPeriods(period2, period1);
            }
        }
        return totalOverlapping;
    }

    public static double getFitness(List<SpecificProfessor> professorList, List<Course> courseList) {
        double fitness = 0.0;
        for (SpecificProfessor professor : professorList) {
            for (Course course : courseList) {
                if (course.getId() != professor.getOption().getCourseId()) {
                    int minOverlapping = Constant.MAX_HOURS;
                    for (SpecificProfessor professor2 : professorList) {
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
