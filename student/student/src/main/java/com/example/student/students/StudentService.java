package com.example.student.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
    return studentRepository.findAll();
}

    public void registerNewStudent(Student student) {
        Optional<Student> studentExists= studentRepository.findStudentByEmail(student.getEmail());
        if(studentExists.isPresent()){
            throw  new IllegalStateException("The student with that email already exists");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if (!studentExists){
            throw new IllegalStateException("The student with id "+id + " does not exist");
        }
        studentRepository.deleteById(id);
    }


    @Transactional
    public void updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(()->new IllegalStateException("The student with the id "+id+ " does not exist"));
        if(name!=null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        Optional<Student> emailExists = studentRepository.findStudentByEmail(email);
        if(email !=null && !Objects.equals(student.getEmail(), email) && !emailExists.isPresent() ){
            student.setEmail(email);
        }


    }
}
