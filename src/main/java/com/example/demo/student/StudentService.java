package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

//@Component
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

    public void addNewStudent(Student student) {
        if(studentRepository.findStudentByEmail(student.getEmail()).isPresent()){
            throw new IllegalStateException("Email is Taken");
        }else{
            studentRepository.save(student);
            System.out.println("Saved");
        }
    }

    public void deleteStudent(Long id) {
        boolean is_student = studentRepository.existsById(id);
        if(is_student){
            studentRepository.deleteById(id);
        }else{
            throw new IllegalStateException("Student with id of "+id.toString()+" dose not exists");
        }
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException(
                   "student with id of "+ studentId.toString() +" dose not exists"
                ));
        if (name != null && name.length() > 0
                && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email != null && email.length() > 0
                && !Objects.equals(student.getEmail(),email)){
            if(studentRepository.findStudentByEmail(email).isPresent()){
                throw new IllegalStateException("Email is Taken");
            }
            student.setEmail(email);
        }
    }
}
