package br.com.alura.schedule.asynctask;

import static br.com.alura.schedule.model.TelephoneType.CELLPHONE;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class editStudentTask extends AsyncTask<Void, Void, Void> {

    private final StudentDao studentDao;
    private final TelephoneDAO telephoneDao;
    private final Student student;
    private final Telephone cellphone;
    private final Telephone landline;
    private final whenStudentEditListener listener;

    public editStudentTask(StudentDao studentDao, TelephoneDAO telephoneDao, Student student, Telephone cellphone, Telephone landline, whenStudentEditListener listener) {
        this.studentDao = studentDao;
        this.telephoneDao = telephoneDao;
        this.student = student;
        this.cellphone = cellphone;
        this.landline = landline;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        studentDao.edit(student);
        bindStudentTelephones(student.getId(), cellphone, landline);
        updateTelephonesId(cellphone, landline);
        telephoneDao.update(cellphone, landline);

        return null;
    }

    private void bindStudentTelephones(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);
        }
    }

    private void updateTelephonesId(Telephone cellphone, Telephone landline) {
        List<Telephone> studentTelephones = telephoneDao.findAllTelephones(student.getId());
        for (Telephone telephone :
                studentTelephones) {
            if (telephone.getType() == CELLPHONE) {
                cellphone.setId(telephone.getId());
            } else {
                landline.setId(telephone.getId());
            }
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.whenEdited();
    }

    public interface whenStudentEditListener {
        void whenEdited();
    }
}
