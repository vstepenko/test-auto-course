package ua.edu.duan.test_course.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import ua.edu.duan.test_course.dto.StudentDto;
import ua.edu.duan.test_course.entity.StudentEntity;
import ua.edu.duan.test_course.repository.StudentRepository;


@Service
public class StudentService {

    private final TestService testService;
    private final StudentRepository studentRepository;

    public StudentService(TestService testService, StudentRepository studentRepository) {
        this.testService = testService;
        this.studentRepository = studentRepository;
    }


    public StudentDto getStudent(String id) {
        return studentRepository.findById(id).map(this::convert).orElse(notFoundResponse());
    }

    public String addStudent(StudentDto studentDto) {
        StudentEntity studentEntity = convert(studentDto);
        studentRepository.save(studentEntity);

        return studentEntity.getId();
    }

    @Transactional
    public String editStudent(String id, StudentDto studentDto) {
        return studentRepository.findById(id)
                .map(studentEntity -> {
                            studentEntity.setName(studentDto.getName());
                            studentEntity.setSurname(studentDto.getSurname());
                            return "Student Edited";
                        })
                .orElse("Student not found");
    }

    @Transactional
    public String deleteStudent(String id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student deleted";
        }
        return "Student not found";

    }

    private StudentEntity convert(StudentDto studentDto) {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setId(studentDto.getId());
        studentEntity.setName(studentDto.getName());
        studentEntity.setSurname(studentDto.getSurname());

        return studentEntity;
    }

    private StudentDto convert(StudentEntity studentEntity) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(studentEntity.getId());
        studentDto.setName(studentEntity.getName());
        studentDto.setSurname(studentEntity.getSurname());

        return studentDto;
    }

    private StudentDto notFoundResponse(){
        StudentDto dto = new StudentDto();
        dto.setError("Not Found");
        return dto;
    }
    public String doSomething() {
        return "Hello World";
    }
}
