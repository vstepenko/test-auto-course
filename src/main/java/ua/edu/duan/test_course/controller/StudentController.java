package ua.edu.duan.test_course.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.edu.duan.test_course.dto.StudentDto;
import ua.edu.duan.test_course.service.StudentService;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String doSomething(){
        return studentService.doSomething();
    }

    @GetMapping(path = "/student")
    public StudentDto getStudent(@RequestParam String id) {
        return studentService.getStudent(id);
    }

    @PostMapping(path = "/student")
    public String addStudent(@RequestBody StudentDto student) {
        return studentService.addStudent(student);
    }

    @PutMapping(path = "/student")
    public String updateStudent(@RequestParam String id, @RequestBody StudentDto student) {
        return studentService.editStudent(id, student);
    }

    @DeleteMapping(path = "/student")
    public String deleteStudent(@RequestParam String id) {
        return studentService.deleteStudent(id);
    }
}