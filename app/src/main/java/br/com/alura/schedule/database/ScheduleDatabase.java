package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.ConstantDatabase.DATABASE_NAME;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V1_TO_V2;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V2_TO_V3_STEP_FOUR;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V2_TO_V3_STEP_ONE;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V2_TO_V3_STEP_THREE;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_V2_TO_V3_STEP_TWO;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.schedule.database.dao.StudentDao;
import br.com.alura.schedule.model.Student;

@Database(entities = {Student.class}, version = 3, exportSchema = false)
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
                }, new Migration(2, 3) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        // CREATE TABLE WITH OLD STRUCTURE
                        database.execSQL(QUERY_MIGRATION_V2_TO_V3_STEP_ONE);
                        // COPY DATA TO THE NEW TABLE
                        database.execSQL(QUERY_MIGRATION_V2_TO_V3_STEP_TWO);
                        // OLD TABLE REMOVE
                        database.execSQL(QUERY_MIGRATION_V2_TO_V3_STEP_THREE);
                        // RENAME NEW TABLE WITH THE NAME OF THE OLD TABLE
                        database.execSQL(QUERY_MIGRATION_V2_TO_V3_STEP_FOUR);
                    }
                })
                .build()
                .getRoomStudentDao();
    }
}
