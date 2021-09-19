package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class SaveStudentTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao studentDao;
    private final TelephoneDAO telephoneDao;
    private final Student student;
    private final Telephone cellphone;
    private final Telephone landline;
    private final whenStudentSavedListener listener;

    public SaveStudentTask(StudentDao studentDao, TelephoneDAO telephoneDao, Student student, Telephone cellphone, Telephone landline, whenStudentSavedListener listener) {
        this.studentDao = studentDao;
        this.telephoneDao = telephoneDao;
        this.student = student;
        this.landline = landline;
        this.cellphone = cellphone;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        int studentId = studentDao.save(student).intValue();
        bindStudentTelephones(studentId, cellphone, landline);
        telephoneDao.save(cellphone, landline);
        return null;
    }

    private void bindStudentTelephones(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.whenSaved();
    }

    public interface whenStudentSavedListener {
        void whenSaved();
    }

}
