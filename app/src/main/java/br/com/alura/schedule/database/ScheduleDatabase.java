package br.com.alura.schedule.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.alura.schedule.database.dao.RoomStudentDao;
import br.com.alura.schedule.model.Student;

@Database(entities = {Student.class}, version = 1,exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    public abstract RoomStudentDao getRoomStudentDao();

    public static RoomStudentDao getInstance(Context context){
        return Room
                .databaseBuilder(context, ScheduleDatabase.class, "schedule.db")
                .allowMainThreadQueries()
                .build()
                .getRoomStudentDao();
    }
}
