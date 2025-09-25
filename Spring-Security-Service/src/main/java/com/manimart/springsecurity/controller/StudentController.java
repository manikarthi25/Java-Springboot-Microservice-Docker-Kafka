package com.manimart.springsecurity.controller;

import com.manimart.springsecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    List<Student> studentList = new ArrayList<>(
            List.of(new Student(1, "Mani", 98),
                    new Student(2, "Mohith", 100)));

    @GetMapping("/get-csrf")
    public ResponseEntity<CsrfToken> getCsrfToken(HttpServletRequest httpServletRequest) {
        CsrfToken csrfToken = (CsrfToken) httpServletRequest.getAttribute("_csrf");
        return new ResponseEntity<CsrfToken>(csrfToken, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<List<Student>>(studentList, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        studentList.add(student);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

}
