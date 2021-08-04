package br.com.alura.schedule.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import alura.schedule.R;
import br.com.alura.schedule.dao.StudentDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.ui.adapter.StudentListAdapter;

public class ListStudentView {

    private final StudentDAO studentDAO;
    private StudentListAdapter adapter;
    private final Context context;

    public ListStudentView(Context context) {
        this.context = context;
        this.adapter = new StudentListAdapter(this.context);
        this.studentDAO  = new StudentDAO();
    }

    public void configureAdapter(ListView studentList) {
        adapter = new StudentListAdapter(context);
        studentList.setAdapter(adapter);
    }

    public void confirmRemoval(@NonNull final MenuItem item) {
        new AlertDialog.Builder(context)
                .setMessage(R.string.confirm_removal_message)
                .setPositiveButton(R.string.yes, (DialogInterface dialog, int which) -> {
                    AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    Student studentChosen = adapter.getItem(menuInfo.position);
                    remove(studentChosen);
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }

    private void remove(Student student) {
        studentDAO.remove(student);
        adapter.remove(student);
    }

    public void updateStudent() {
        adapter.update(studentDAO.all());
    }
}
