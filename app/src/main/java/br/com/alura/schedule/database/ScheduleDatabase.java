package br.com.alura.schedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    private static final String DATABASE_NAME = "schedule.db";

    public abstract StudentDao getRoomStudentDao();

    public static StudentDao getInstance(Context context){

        return Room
                .databaseBuilder(context, ScheduleDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .build()
                .getRoomStudentDao();
    }
}
