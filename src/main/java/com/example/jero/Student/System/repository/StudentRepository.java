package com.example.jero.Student.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jero.Student.System.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}   