package br.com.alura.schedule.ui.activity;

import static br.com.alura.schedule.model.TelephoneType.CELLPHONE;
import static br.com.alura.schedule.model.TelephoneType.LANDLINE;
import static br.com.alura.schedule.ui.activity.ConstantActivities.KEY_STUDENT;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import alura.schedule.R;
import br.com.alura.schedule.database.ScheduleDatabase;
import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

public class FormStudentActivity extends AppCompatActivity {

    private EditText fieldName;
    private EditText fieldCellPhone;
    private EditText fieldLandline;
    private EditText fieldEmail;
    private StudentDao studentDao;
    private TelephoneDAO telephoneDao;
    private Student student;
    private List<Telephone> studentTelephones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_student);

        //dao initialization
        studentDao = ScheduleDatabase.getInstance(this).getRoomStudentDao();
        telephoneDao = ScheduleDatabase.getInstance(this).getTelephoneDao();

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
        //List<Telephone> telephones = telephoneDao.findAllTelephones(student.getId());
        studentTelephones = telephoneDao.findAllTelephones(student.getId());
        for (Telephone telephone :
                studentTelephones) {
            if (telephone.getType() == CELLPHONE) {
                fieldCellPhone.setText(telephone.getNumber());
            } else {
                fieldLandline.setText(telephone.getNumber());
            }
        }
        fieldEmail.setText(student.getEmail());
    }

    private void finishForm() {
        fillStudent();
        if (student.hasValidId()) {
            studentDao.edit(student);

            String cellphoneNumber = fieldCellPhone.getText().toString();
            Telephone cellphone = new Telephone(cellphoneNumber, CELLPHONE, student.getId());

            String landlineNumber = fieldLandline.getText().toString();
            Telephone landline = new Telephone(landlineNumber, LANDLINE, student.getId());

            for (Telephone telephone :
                    studentTelephones) {
                if (telephone.getType() == CELLPHONE) {
                    cellphone.setId(telephone.getId());
                } else {
                    landline.setId(telephone.getId());
                }
            }

            telephoneDao.update(cellphone, landline);
        } else {
            //int studentId = studentDao.save(student).intValue();
            String cellphoneNumber = fieldCellPhone.getText().toString();
            Telephone cellphone = new Telephone(cellphoneNumber, CELLPHONE, student.getId());

            String landlineNumber = fieldLandline.getText().toString();
            Telephone telephone = new Telephone(landlineNumber, LANDLINE, student.getId());

            telephoneDao.save(cellphone, telephone);
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
