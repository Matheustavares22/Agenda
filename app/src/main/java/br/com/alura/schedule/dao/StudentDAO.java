package br.com.alura.schedule.dao;

import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.schedule.model.Student;

public class StudentDAO {

    private final static List<Student> STUDENTS = new ArrayList<>();
    private static int idCount = 1;

    public void save(Student student) {
        student.setId(idCount);
        STUDENTS.add(student);
        idUpdate();
    }

    private void idUpdate() {
        idCount++;
    }

    public void edit(Student student) {
        Student studentFound = findStudentById(student);
        if (studentFound != null) {
            int studentPosition = STUDENTS.indexOf(studentFound);
            STUDENTS.set(studentPosition, student);
        }
    }

    @Nullable
    private Student findStudentById(Student student) {
        for (Student a :
                STUDENTS) {
            if (a.getId() == student.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Student> all() {
        return new ArrayList<>(STUDENTS);
    }

    public void remove(Student student) {
        Student studentReturned = findStudentById(student);
        if(studentReturned != null) {
            Log.i("removeStudent", "Student removed: " + studentReturned.getName());
            STUDENTS.remove(studentReturned);
        }
    }
}
