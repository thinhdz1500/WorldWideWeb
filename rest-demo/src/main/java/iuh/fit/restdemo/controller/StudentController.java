package iuh.fit.restdemo.controller;

import iuh.fit.restdemo.models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private List<Student> studentList;
    public StudentController() {
        studentList = new ArrayList<Student>();
    }
    public List<Student> getStudentList() {
        studentList.add(new Student(1,"Thinh", "Khuong An"));
        studentList.add(new Student(2,"Thinh", "Khuong An"));
        studentList.add(new Student(3,"Thinh", "Khuong An"));
        return studentList;
    }
}
