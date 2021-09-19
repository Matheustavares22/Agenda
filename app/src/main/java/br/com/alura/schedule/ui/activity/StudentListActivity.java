package br.com.alura.schedule.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import alura.schedule.R;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.ui.ListStudentView;

import static br.com.alura.schedule.ui.activity.ConstantActivities.KEY_STUDENT;

public class StudentListActivity extends AppCompatActivity {

    private ListStudentView listStudentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.student_list);
        listStudentView = new ListStudentView(this);
        configureFABStudent();
        configureList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_student_list_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_student_list_remove) {
            listStudentView.confirmRemoval(item);
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listStudentView.updateStudentList();
    }

    private void configureList() {
        ListView studentList = findViewById(R.id.activity_student_list_listview);
        listStudentView.configureAdapter(studentList);
        configureListenerClickByItem(studentList);
        registerForContextMenu(studentList);
    }

    private void configureListenerClickByItem(ListView studentList) {
        studentList.setOnItemClickListener((AdapterView<?> adapterView, View view, int position, long id) -> {
            Student studentChosen = (Student) adapterView.getItemAtPosition(position);
            openFormEditingStudentMode(studentChosen);
        });
    }

    private void configureFABStudent() {
        FloatingActionButton addStudents = findViewById(R.id.activity_student_list_fab_new_student);
        addStudents.setOnClickListener((View v) -> openFormInsertStudentMode());
    }

    private void openFormInsertStudentMode() {
        startActivity(new Intent(this, FormStudentActivity.class));
    }

    private void openFormEditingStudentMode(Student student) {
        Intent goToFormActivity = new Intent(this, FormStudentActivity.class);
        goToFormActivity.putExtra(KEY_STUDENT, student);
        startActivity(goToFormActivity);
    }
}
