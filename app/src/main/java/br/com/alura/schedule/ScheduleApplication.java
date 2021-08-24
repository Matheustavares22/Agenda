package br.com.alura.schedule;

import android.app.Application;

import br.com.alura.schedule.dao.StudentDAO;
import br.com.alura.schedule.model.Student;

public class ScheduleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        createStudentsForTest();
    }

    private void createStudentsForTest() {
//        StudentDAO studentDAO = new StudentDAO();
//        studentDAO.save(new Student("Matheus", "14997228948", "matheus@email.com"));
//        studentDAO.save(new Student("Alex", "14997528989", "kamila@email.com"));
    }
}
