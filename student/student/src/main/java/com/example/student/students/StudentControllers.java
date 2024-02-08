package com.example.student.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentControllers {
    private StudentService studentService;

    @Autowired
    public StudentControllers(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
     return studentService.getStudents();
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.registerNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id ,
                              @RequestBody UpdateStudentRequest request
                              ){
        studentService.updateStudent(id, request.getName(), request.getEmail());
    }


}


class UpdateStudentRequest{
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
