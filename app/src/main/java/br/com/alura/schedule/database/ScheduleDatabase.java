package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.ConstantDatabase.DATABASE_NAME;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V1_TO_V2;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;

@Database(entities = {Student.class}, version = 2, exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {


    public abstract StudentDao getRoomStudentDao();

    public static StudentDao getInstance(Context context) {

        return Room
                .databaseBuilder(context, ScheduleDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries()
                .addMigrations(new Migration(1, 2) {

                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL(QUERY_MIGRATION_V1_TO_V2);
                    }
                })
                .build()
                .getRoomStudentDao();
    }
}
