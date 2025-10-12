package com.example.demo.student;

import ch.qos.logback.core.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final  StudentRepository studentRepository;
    public List<Student> get() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findAllByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("This email is taken");
        }

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(exists){
        studentRepository.deleteById(studentId);
        }

        else    {
            throw new IllegalStateException("Student with id" + studentId + "not found");
        }

    }

    //StringUtils.hasText - checks if string is empty or null
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(()-> new IllegalStateException("Student with id" + studentId + "not found"));

        if (StringUtils.hasText(name) && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }


        if(StringUtils.hasText(email) && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findAllByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("This email is taken");
            }
            student.setEmail(email);
        }

    }
}
