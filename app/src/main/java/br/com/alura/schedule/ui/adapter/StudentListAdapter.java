package br.com.alura.schedule.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import alura.schedule.R;
import br.com.alura.schedule.database.ScheduleDatabase;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class StudentListAdapter extends BaseAdapter {

    private final List<Student> students = new ArrayList<>();
    private final Context context;

    public StudentListAdapter(Context context) {
        this.context = context;
    }

    public void update(List<Student> students) {
        this.students.clear();
        this.students.addAll(students);
        notifyDataSetChanged();
    }

    public void remove(Student student) {
        students.remove(student);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return students.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        createView(viewGroup);
        View view;

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);
        } else {
            view = convertView;
        }

        Student studentReturned = students.get(position);
        linkInformation(view, studentReturned);
        return view;
    }

    private void linkInformation(View view, Student student) {
        TextView name = view.findViewById(R.id.item_student_name);
        name.setText(student.getName());

        TelephoneDAO telephoneDao = ScheduleDatabase.getInstance(context).getTelephoneDao();
        Telephone firstTelephone = telephoneDao.findFirstTelephone(student.getId());
        TextView cellPhone = view.findViewById(R.id.item_student_cellphone);
        cellPhone.setText(firstTelephone.getNumber());
    }

    @SuppressWarnings("UnusedReturnValue")
    private View createView(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);
    }

}
