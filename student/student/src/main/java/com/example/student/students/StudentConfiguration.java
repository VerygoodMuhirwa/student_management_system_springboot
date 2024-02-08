package com.example.student.students;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfiguration {
@Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
           Student verygood= new Student("Verygood Muhirwa", "verygoodmuhirwa2@gmail.com", LocalDate.of(2005, Month.SEPTEMBER,14));
            Student souvenir= new Student("Turinumugisha Souvenir", "turinumugishasouvenir@gmail.com", LocalDate.of(2003, Month.SEPTEMBER,14));
            studentRepository.saveAll(List.of(verygood, souvenir));
        };

    }


}
