package com.example.Student_Library_Management_System.Repositories;

import com.example.Student_Library_Management_System.Models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Integer> {

    Student findByEmail(String email);

    List<Student> findByCountry(String country);


}
