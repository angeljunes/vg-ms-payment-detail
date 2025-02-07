package com.inventary.enriqueta.presentation.controller;

import com.inventary.enriqueta.application.service.StudentService;
import com.inventary.enriqueta.domain.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/public/student/api/v1/")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService attorneyService) {
        this.studentService = attorneyService;
    }

    @GetMapping
    public Flux<Student> getAllStudent() {
        return studentService.listAllActive();
    }
}