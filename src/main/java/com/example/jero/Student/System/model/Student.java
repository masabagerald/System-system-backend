package com.example.jero.Student.System.model;

import com.example.jero.Student.System.model.enums.StudentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    Integer id;
    String name; 
    String email;
    String phone;
    String dateOfBirth;
    String gender;
    String program;
    String department;
    String academicYear;
    String yearOfStudy;

    @Enumerated(EnumType.STRING)
    StudentStatus status;

}

