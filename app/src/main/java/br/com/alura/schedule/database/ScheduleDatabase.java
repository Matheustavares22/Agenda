package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.ScheduleMigrations.MIGRATIONS;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import br.com.alura.schedule.converters.database.ConvertorTelephoneType;
import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.database.dao.TelephoneDAO;
import br.com.alura.schedule.model.Student;
import br.com.alura.schedule.model.Telephone;

@Database(entities = {Student.class, Telephone.class}, version = 2, exportSchema = false)
@TypeConverters(ConvertorTelephoneType.class)
public abstract class ScheduleDatabase extends RoomDatabase {

    public static final String SCHEDULE_DB = "schedule.db";

    public abstract TelephoneDAO getTelephoneDao();

    public abstract StudentDao getRoomStudentDao();

    public static ScheduleDatabase getInstance(Context context) {

        return Room
                .databaseBuilder(context, ScheduleDatabase.class, SCHEDULE_DB)
                .addMigrations(MIGRATIONS)
                .build();
    }

}
