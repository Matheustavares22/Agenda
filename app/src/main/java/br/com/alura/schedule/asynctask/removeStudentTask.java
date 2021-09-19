package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.ui.adapter.StudentListAdapter;

public class removeStudentTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao dao;
    private final StudentListAdapter adapter;
    private final Student student;

    public removeStudentTask(StudentDao dao, StudentListAdapter adapter, Student student) {
        this.dao = dao;
        this.adapter = adapter;
        this.student = student;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        dao.remove(student);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        adapter.remove(student);
    }
}
