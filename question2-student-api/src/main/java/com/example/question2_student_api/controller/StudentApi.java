package com.example.question2_student_api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.example.question2_student_api.model.Student;

@RestController()
@RequestMapping("/api/students")
public class StudentApi {

    List<Student> students = new ArrayList<>(); 

    public StudentApi(){
        students.add(new Student(1,"Preacher", "Y", "preacher@auca.ac.rw", "Software Engineering",3.49));
        students.add(new Student(2,"Joc", "Pink", "jocpink@auca.ac.rw", "Software Engineering",4.0));
        students.add(new Student(3,"Coosin", "D", "coosin@auca.ac.rw", "Networking",3.29));
        students.add(new Student(4,"Karlie", "A", "akarlie@auca.ac.rw", "Information Management",3.19));
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable long id){
        return students.stream()
                    .filter(el-> el.getId() == id)
                    .findFirst()
                    .orElseThrow(()->
                        new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Student with id: "+ id+" Not Found ..."
                        )
                    );
    }

    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major){
        return students.stream()
                    .filter(el->el.getMajor().contains(major))
                    .collect(Collectors.toList());
    }

    @GetMapping("/filter")
    public List<Student> getStudentByGPA(@RequestParam double gpa){
        return students.stream()
                    .filter(el->el.getGPA()>=gpa)
                    .collect(Collectors.toList());
    }

    @PostMapping()
    public Student registerNewStudent(@RequestBody Student newStudent){
        students.add(newStudent);
        return newStudent;
    }

    @PutMapping("/{id}")
    public Student updateStudentInfo(@PathVariable long id, @RequestBody Student student){
        Student std = students.stream()
                            .filter(el->el.getId()== id)
                            .findFirst()
                            .orElseThrow(()->
                                new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Student with Id: "+id+", Not Found !!"
                                )
                            );
        std.setId(student.getId());
        std.setFirstName(student.getFirstName());
        std.setLastName(student.getLastName());
        std.setEmail(student.getEmail());
        std.setMajor(student.getMajor());
        std.setGPA(student.getGPA());

        return std;
    }
}
