package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/student")
// This class exposes REST API endpoints for Student operations.
public class StudentController {

    private final StudentService studentService;

    // outdated method
    //  @Autowired
    // public StudentController(StudentService studentService){
    //     this.studentService = studentService;
    //  }

    // Returns all students
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // Adds a new student (expects JSON body)
    @PostMapping
    public ResponseEntity<String> registerNewStudent(@RequestBody Student student) {

        studentService.addNewStudent(student);
        return ResponseEntity.ok("Student with id " + student.getId() + " has been added to the database");
    }

    //take the id from the path and delete student with that id
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok("Student with id " + studentId + " deleted");

    }

    // Updates a student
    @PutMapping(path = "{studentId}")
    public ResponseEntity<String> updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody Map<String, Object> updates) {
        String name = (String) updates.get("name");
        String email = (String) updates.get("email");

        studentService.updateStudent(studentId, name, email);
        return ResponseEntity.ok("Student with id " + studentId + " has been updated");
    }
}
