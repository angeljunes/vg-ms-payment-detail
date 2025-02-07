package com.inventary.enriqueta.domain.repository;

import com.inventary.enriqueta.domain.model.Student;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface StudentRepository extends ReactiveMongoRepository<Student, String> {


}
