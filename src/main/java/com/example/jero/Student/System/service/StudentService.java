package com.example.jero.Student.System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jero.Student.System.model.Student;
import com.example.jero.Student.System.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(Integer id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudent(Integer id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(student.getName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setPhone(student.getPhone());
            existingStudent.setDateOfBirth(student.getDateOfBirth());
            existingStudent.setGender(student.getGender());
            existingStudent.setProgram(student.getProgram());
            existingStudent.setDepartment(student.getDepartment());
            existingStudent.setAcademicYear(student.getAcademicYear());
            existingStudent.setYearOfStudy(student.getYearOfStudy());
            existingStudent.setStatus(student.getStatus());
            return studentRepository.save(existingStudent);
        }
        return null;
    }  

    public void deleteStudentById(Integer id) {
        studentRepository.deleteById(id);
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }   





}
