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
import br.com.alura.schedule.model.TelephoneType;

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
            student = data.getExtras().getParcelable(KEY_STUDENT);
            fillFields();
        } else {
            setTitle(R.string.student_list);
            student = new Student();
        }
    }

    private void fillFields() {
        fieldName.setText(student.getName());
        fieldEmail.setText(student.getEmail());
        fillTelephones();
    }

    private void fillTelephones() {
        studentTelephones = telephoneDao.findAllTelephones(student.getId());
        for (Telephone telephone :
                studentTelephones) {
            if (telephone.getType() == CELLPHONE) {
                fieldCellPhone.setText(telephone.getNumber());
            } else {
                fieldLandline.setText(telephone.getNumber());
            }
        }
    }

    private void finishForm() {
        fillStudent();

        Telephone cellphone = createTelephone(fieldCellPhone, CELLPHONE);
        Telephone landline = createTelephone(fieldLandline, LANDLINE);

        if (student.hasValidId()) {
            editStudent(cellphone, landline);
        } else {
            saveStudent(cellphone, landline);
        }
        finish();
    }

    private Telephone createTelephone(EditText fieldCellPhone, TelephoneType cellphone2) {
        String cellphoneNumber = fieldCellPhone.getText().toString();
        return new Telephone(cellphoneNumber, cellphone2);
    }

    private void saveStudent(Telephone cellphone, Telephone landline) {
        int studentId = studentDao.save(student).intValue();
        bindStudentTelephones( studentId, cellphone, landline);
        telephoneDao.save(cellphone, landline);
    }

    private void editStudent(Telephone cellphone, Telephone landline) {
        studentDao.edit(student);
        bindStudentTelephones(student.getId(), cellphone, landline);
        updateTelephonesId(cellphone, landline);
        telephoneDao.update(cellphone, landline);
    }

    private void updateTelephonesId(Telephone cellphone, Telephone landline) {
        for (Telephone telephone :
                studentTelephones) {
            if (telephone.getType() == CELLPHONE) {
                cellphone.setId(telephone.getId());
            } else {
                landline.setId(telephone.getId());
            }
        }
    }

    private void bindStudentTelephones(int studentId, Telephone... telephones) {
        for (Telephone telephone :
                telephones) {
            telephone.setStudentId(studentId);
        }
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
