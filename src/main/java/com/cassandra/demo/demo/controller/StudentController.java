package com.cassandra.demo.demo.controller;

import com.cassandra.demo.demo.entity.Student;
import com.cassandra.demo.demo.exception.ResourceNotFoundException;
import com.cassandra.demo.demo.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student) throws JsonProcessingException {
        Student student1 = studentRepository.save(new Student(UUID.randomUUID(),student.getName(), student.getAddress(), student.isStatus()));
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValueAsString(student1);
        return new ResponseEntity<>(student1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student with id "+ id + " not found"));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> student = studentRepository.findAll();
        return new ResponseEntity<>(student,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") UUID id, @RequestBody Student student) {
        Optional<Student> student1 = studentRepository.findById(id);

        if (student1.isPresent()) {
            Student newStudent = student1.get();
            newStudent.setName(student.getName());
            newStudent.setAddress(student.getAddress());
            newStudent.setStatus(student.isStatus());
            return new ResponseEntity<>(studentRepository.save(newStudent), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Student>> deleteStudent(@PathVariable("id") UUID id) {
        studentRepository.deleteById(id);
        return new ResponseEntity<>(studentRepository.findAll(), HttpStatus.OK);
    }
}
