package com.school.school.controller;

//import com.example.student.entity.Student;
//import com.example.student.service.StudentService;

import com.school.school.model.Student;
import com.school.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping("api/v1")
public class StudentController {
    @Autowired
    private StudentService stService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(@RequestParam Integer pageNumber, Integer pageSize) {
        return new ResponseEntity<List<Student>>(stService.getStudents(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        return new ResponseEntity<Student>(stService.getSingleStudent(id), HttpStatus.OK);
    }

    @PostMapping("students")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(stService.saveStudent(student), HttpStatus.CREATED);
    }

    @PutMapping("students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        student.setId(id);
        return new ResponseEntity<Student>(stService.updateStudent(student), HttpStatus.OK);
    }

    @DeleteMapping("/students")
    public ResponseEntity<HttpStatus> deleteStudent(@RequestParam Long id) {
        stService.deleteStudent(id);
        System.out.println("student " + id + " details DELETED");
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/students/filterByName")
    public ResponseEntity<List<Student>> getStudentsByName(@RequestParam String name) {
        return new ResponseEntity<List<Student>>(stService.getStudentsByName(name), HttpStatus.OK);
    }

    @GetMapping("/students/filterByNameAndLocation")
    public ResponseEntity<List<Student>> getStudentsByNameAndLocation(@RequestParam String name, @RequestParam String location) {
        return new ResponseEntity<List<Student>>(stService.getStudentsByNameAndLocation(name, location), HttpStatus.OK);
    }

    @GetMapping("students/filterByNameKeyword")
    public ResponseEntity<List<Student>> getStudentsByNameKeyword(@RequestParam String keyword) {
        return new ResponseEntity<List<Student>>(stService.getStudentsByNameKeyword(keyword), HttpStatus.OK);
    }
}