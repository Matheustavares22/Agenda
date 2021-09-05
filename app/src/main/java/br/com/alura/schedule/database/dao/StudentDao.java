package br.com.alura.schedule.database.dao;

import static br.com.alura.schedule.database.constant.StudentQueries.QUERY_GET_STUDENT_DATA;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import br.com.alura.schedule.model.Student;

@Dao
public abstract class StudentDao {

    @Insert
    public abstract void save(Student student);

    @Delete
    public abstract void remove(Student student);

    @Query(QUERY_GET_STUDENT_DATA)
    public abstract List<Student> all();

    @Update
    public abstract void edit(Student student);

}
