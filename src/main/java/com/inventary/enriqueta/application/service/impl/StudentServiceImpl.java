package com.inventary.enriqueta.application.service.impl;

import com.inventary.enriqueta.application.service.StudentService;
import com.inventary.enriqueta.domain.model.Student;
import com.inventary.enriqueta.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class StudentServiceImpl implements StudentService {



    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Flux<Student> listAllActive() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Mono<Student> createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> deleteStudent(String id) {
        studentRepository.deleteById(id);
        return Mono.empty();
    }

}
