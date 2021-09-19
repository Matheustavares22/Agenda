package br.com.alura.schedule.asynctask;

import android.os.AsyncTask;

import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Telephone;

public class FindFirstTelephoneTask extends AsyncTask<Void, Void, Telephone> {

    private final TelephoneDAO dao;
    private final int studentId;
    private final firstTelephoneFoundListener listener;


    public FindFirstTelephoneTask(TelephoneDAO dao, int studentId, firstTelephoneFoundListener listener) {
        this.dao = dao;
        this.studentId = studentId;
        this.listener = listener;
    }

    @Override
    protected Telephone doInBackground(Void... voids) {
        return dao.findFirstTelephone(studentId);
    }

    @Override
    protected void onPostExecute(Telephone firstTelephone) {
        super.onPostExecute(firstTelephone);
        listener.whenFound(firstTelephone);
    }

    public interface firstTelephoneFoundListener {
        void whenFound(Telephone telephoneFound);
    }

}
