package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public ResponseEntity<Long> createStudent(@RequestBody Student student) {

       // studentService.addNewStudent(student);
        studentService.saveStudent(student);
        return new ResponseEntity<>(student.getId(),HttpStatus.CREATED);
        //return ResponseEntity.ok("Student with id " + student.getId() + " has been added to the database");
    }

    @PostMapping("/auto/club")
    public ResponseEntity<Long> createStudentAndAutoClub(@RequestBody Student student){
        Student savedStudent = studentService.saveStudentAndCreateClub(student);
        return new ResponseEntity<>(savedStudent.getId(), HttpStatus.CREATED);

    }

    //take the id from the path and delete student with that id
    @DeleteMapping(path = "{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable("studentId") Long studentId) {

        try{
            studentService.deleteStudent(studentId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

       // studentService.deleteStudent(studentId);
       // return new ResponseEntity<>(HttpStatus.OK);
       // return ResponseEntity.ok("Student with id " + studentId + " deleted");

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

    @PutMapping("/{studentId}/club/{clubId}")
    public ResponseEntity<Student> assignStudentClub(@PathVariable Long studentId ,@PathVariable Long clubId){
        try{
            return  new ResponseEntity<>(studentService.assignStudentClub(studentId,clubId), HttpStatus.OK);
        }catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

}
