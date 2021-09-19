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

import alura.schedule.R;
import br.com.alura.schedule.asynctask.FindAllTelephonesTask;
import br.com.alura.schedule.asynctask.SaveStudentTask;
import br.com.alura.schedule.asynctask.EditStudentTask;
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
        new FindAllTelephonesTask(telephoneDao, student, telephones -> {
            for (Telephone telephone :
                    telephones) {
                if (telephone.getType() == CELLPHONE) {
                    fieldCellPhone.setText(telephone.getNumber());
                } else {
                    fieldLandline.setText(telephone.getNumber());
                }
            }
        }).execute();
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
    }

    private Telephone createTelephone(EditText fieldCellPhone, TelephoneType cellphone2) {
        String cellphoneNumber = fieldCellPhone.getText().toString();
        return new Telephone(cellphoneNumber, cellphone2);
    }

    private void saveStudent(Telephone cellphone, Telephone landline) {
        new SaveStudentTask(studentDao, telephoneDao, student, cellphone, landline, this::finish).execute();
    }

    private void editStudent(Telephone cellphone, Telephone landline) {
        new EditStudentTask(studentDao, telephoneDao, student, cellphone, landline, this::finish).execute();
    }

    private void fieldInitialization() {
        fieldName = findViewById(R.id.activity_form_student_name);
        fieldCellPhone = findViewById(R.id.activity_form_student_cellphone);
        fieldLandline = findViewById(R.id.activity_form_student_landline);
        fieldEmail = findViewById(R.id.activity_form_student_email);
    }

    private void fillStudent() {
        String name = fieldName.getText().toString();
        String email = fieldEmail.getText().toString();

        student.setName(name);
        student.setEmail(email);
    }

}
