package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// Automatically generates a constructor for all final fields (Dependency Injection)
public class StudentService {

    // Injected repository (final = immutable dependency)
    private final StudentRepository studentRepository;

    // Returns all students ordered by their ID (ascending)
    public List<Student> getStudents() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    // Adds a new student if the email is unique
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("This email is taken");
        }

        studentRepository.save(student);
    }

    // Deletes a student by their ID
    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if (exists) {
            studentRepository.deleteById(studentId);
        } else {
            throw new IllegalStateException("Student with id" + studentId + "not found");
        }

    }

    // Updates student's name or email (if provided)
    @Transactional // Ensures that all DB operations inside are executed in one transaction
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(() -> new IllegalStateException("Student with id" + studentId + "not found"));
//StringUtils.hasText - checks if string is empty or null
        if (StringUtils.hasText(name) && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }


        if (StringUtils.hasText(email) && !Objects.equals(student.getEmail(), email)) {
            Optional<Student> studentOptional = studentRepository.findByEmail(email);
            if (studentOptional.isPresent()) {
                throw new IllegalStateException("This email is taken");
            }
            student.setEmail(email);
        }

    }

    public Student getStudentOrThrow(Long id){
        return studentRepository.findById(id).orElseThrow(()-> new NoSuchElementException(" element with id " +id+" does not exist" ));
    }
}

