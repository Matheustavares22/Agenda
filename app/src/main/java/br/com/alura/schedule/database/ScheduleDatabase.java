package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.ConstantDatabase.DATABASE_NAME;
import static br.com.alura.schedule.database.ScheduleMigrations.MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;

@Database(entities = {Student.class}, version = 5, exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    public abstract StudentDao getRoomStudentDao();

    public static StudentDao getInstance(Context context) {

        return Room
                .databaseBuilder(context, ScheduleDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(MIGRATIONS)
                .build()
                .getRoomStudentDao();
    }
}
