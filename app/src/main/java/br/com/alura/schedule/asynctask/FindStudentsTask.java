package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.ui.adapter.StudentListAdapter;

public class FindStudentsTask extends AsyncTask<Void, Void,List<Student>> {

    private StudentDao dao;
    private StudentListAdapter adapter;

    public FindStudentsTask(StudentDao dao, StudentListAdapter adapter) {
        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Student> doInBackground(Void[] objects) {
        List<Student> students = dao.all();
        return students;
    }

    @Override
    protected void onPostExecute(List<Student> allStudents) {
        super.onPostExecute(allStudents);
        adapter.update(allStudents);
    }
}
