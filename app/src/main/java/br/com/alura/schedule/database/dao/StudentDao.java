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
public interface StudentDao {

    @Insert
    Long save(Student student);

    @Delete
    void remove(Student student);

    @Query(QUERY_GET_STUDENT_DATA)
    List<Student> all();

    @Update
    void edit(Student student);

}
