package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_1_TO_2;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_2_TO_3_STEP_FOUR;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_2_TO_3_STEP_ONE;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_2_TO_3_STEP_THREE;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_2_TO_3_STEP_TWO;
import static br.com.alura.schedule.database.ConstantDatabase.QUERY_MIGRATION_3_TO_4;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

class ScheduleMigrations {

    private static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(QUERY_MIGRATION_1_TO_2);
        }
    };

    private static final Migration MIGRATION_2_TO_3 = new Migration(2, 3) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_ONE);   // CREATE TABLE WITH OLD STRUCTURE
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_TWO);   // COPY DATA TO THE NEW TABLE
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_THREE); // OLD TABLE REMOVE
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_FOUR);  // RENAME NEW TABLE WITH THE NAME OF THE OLD TABLE
        }
    };

    private static final Migration MIGRATION_3_TO_4 = new Migration(3, 4) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(QUERY_MIGRATION_3_TO_4);
        }
    };

    private static final Migration MIGRATION_4_TO_5 = new Migration(4, 5) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_ONE);
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_TWO);
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_THREE);
            database.execSQL(QUERY_MIGRATION_2_TO_3_STEP_FOUR);
        }
    };

    static final Migration[] MIGRATIONS = {
            MIGRATION_1_TO_2,
            MIGRATION_2_TO_3,
            MIGRATION_3_TO_4,
            MIGRATION_4_TO_5
    };
}
