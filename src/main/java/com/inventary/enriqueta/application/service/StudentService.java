package com.inventary.enriqueta.application.service;

import com.inventary.enriqueta.domain.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> listAllActive();
    Mono<Student> createStudent (Student student);
    Mono<Student> deleteStudent(String id);
    Mono<Student> findById(String id);

}
