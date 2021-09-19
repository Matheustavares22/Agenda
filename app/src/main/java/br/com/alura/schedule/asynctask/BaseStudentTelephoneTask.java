package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

abstract class BaseStudentTelephoneTask  extends AsyncTask<Void, Void, Void> {

    protected StudentDao studentDao;
    protected TelephoneDAO telephoneDao;
    protected Student student;
    protected Telephone cellphone;
    protected Telephone landline;
    protected whenStudentActionComplete listener;

    protected BaseStudentTelephoneTask(StudentDao studentDao, TelephoneDAO telephoneDao, Student student, Telephone cellphone, Telephone landline, whenStudentActionComplete listener) {
        this.studentDao = studentDao;
        this.telephoneDao = telephoneDao;
        this.student = student;
        this.cellphone = cellphone;
        this.landline = landline;
    }

    protected void bindStudentTelephones(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.whenActionComplete();
    }

    public interface whenStudentActionComplete{
        void whenActionComplete();
    }
}
