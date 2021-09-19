package br.com.alura.schedule.asynctask;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class SaveStudentTask extends BaseStudentTelephoneTask {

    public SaveStudentTask(StudentDao studentDao, TelephoneDAO telephoneDao, Student student, Telephone cellphone, Telephone landline, whenStudentActionComplete listener) {
        super(studentDao, telephoneDao, student, cellphone, landline, listener);
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

}
