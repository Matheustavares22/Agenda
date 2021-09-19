package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class FindAllTelephonesTask extends AsyncTask<Void, Void, List<Telephone>> {

    private final TelephoneDAO telephoneDao;
    private final Student student;
    private TelephoneStudentsFoundListener listener;

    public FindAllTelephonesTask(TelephoneDAO telephoneDao, Student student, TelephoneStudentsFoundListener listener) {
        this.telephoneDao = telephoneDao;
        this.student = student;
        this.listener = listener;
    }

    @Override
    protected List<Telephone> doInBackground(Void... voids) {
        return telephoneDao.findAllTelephones(student.getId());
    }

    @Override
    protected void onPostExecute(List<Telephone> telephones) {
        super.onPostExecute(telephones);
        listener.whenFounded(telephones);
    }

    public interface TelephoneStudentsFoundListener{
        void whenFounded(List<Telephone> telephones);
    }
}
