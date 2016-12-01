package com.gpi.scheduling.controller;

import com.gpi.scheduling.model.Course;
import com.gpi.scheduling.model.Professor;
import com.gpi.scheduling.model.SpecificProfessor;
import com.gpi.scheduling.model.Student;
import com.gpi.scheduling.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Diego Mansilla on 12/1/2016.
 */

@RestController
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<SpecificProfessor>> getProfessorOptionsForSemester(
            @RequestParam(value = "semester") int semester,
            @RequestParam(value = "courseList")List<Course> courseList,
            @RequestParam(value = "professorList")List<Professor> professorList,
            @RequestParam(value = "studentList")List<Student> studentList) {
        List<SpecificProfessor> professorOptions = scheduleService.getProfessorOptionsForSemester(semester, courseList, professorList, studentList);
        return ResponseEntity.ok(professorOptions);
    }

}
