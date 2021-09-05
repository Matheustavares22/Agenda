package br.com.alura.schedule.ui.activity;

import static br.com.alura.schedule.ui.activity.ConstantActivities.KEY_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import alura.schedule.R;
import br.com.alura.schedule.database.ScheduleDatabase;
import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;

public class FormStudentActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldCellPhone;
    private EditText fieldLandline;
    private EditText fieldEmail;
    private StudentDao dao;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        dao = ScheduleDatabase.getInstance(this).getRoomStudentDao();

        fieldInitialization();
        loadStudent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_student_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_form_student_menu_save) {
            finishForm();
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadStudent() {
        Intent data = getIntent();
        if (data.hasExtra(KEY_STUDENT)) {
            setTitle(R.string.editing_student);
            student = (Student) data.getExtras().getParcelable(KEY_STUDENT);
            fillFields();
        } else {
            setTitle(R.string.student_list);
            student = new Student();
        }
    }

    private void fillFields() {
        fieldName.setText(student.getName());
//        fieldCellPhone.setText(student.getCellPhone());
//        fieldLandline.setText(student.getLandline());
        fieldEmail.setText(student.getEmail());
    }

    private void finishForm() {
        fillStudent();
        if (student.hasValidId()) {
            dao.edit(student);
        } else {
            dao.save(student);
        }
        finish();
    }

    private void fieldInitialization() {
        fieldName = findViewById(R.id.activity_form_student_name);
        fieldCellPhone = findViewById(R.id.activity_form_student_cellphone);
        fieldLandline = findViewById(R.id.activity_form_student_landline);
        fieldEmail = findViewById(R.id.activity_form_student_email);
    }

    private void fillStudent() {
        String name = fieldName.getText().toString();
        String cellPhone = fieldCellPhone.getText().toString();
        String landline = fieldLandline.getText().toString();
        String email = fieldEmail.getText().toString();

        student.setName(name);
//        student.setCellPhone(cellPhone);
//        student.setLandline(landline);
        student.setEmail(email);
    }

}
