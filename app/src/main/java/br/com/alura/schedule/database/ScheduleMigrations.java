package br.com.alura.schedule.database;

import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_FIVE;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_FOUR;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_ONE;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_SEVEN;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_SIX;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_THREE;
import static br.com.alura.schedule.database.constant.MigrationsQueries.MIGRATION_1_TO_2_STEP_TWO;
import static br.com.alura.schedule.model.TelephoneType.LANDLINE;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import br.com.alura.schedule.model.TelephoneType;

class ScheduleMigrations {

    private static final Migration MIGRATION_1_TO_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL(MIGRATION_1_TO_2_STEP_ONE);
            database.execSQL(MIGRATION_1_TO_2_STEP_TWO);
            database.execSQL(MIGRATION_1_TO_2_STEP_THREE);
            database.execSQL(MIGRATION_1_TO_2_STEP_FOUR);
            database.execSQL(MIGRATION_1_TO_2_STEP_FIVE + " ?" , new TelephoneType[] {LANDLINE});
            database.execSQL(MIGRATION_1_TO_2_STEP_SIX);
            database.execSQL(MIGRATION_1_TO_2_STEP_SEVEN);
        }

    };

    static final Migration[] MIGRATIONS = {
            MIGRATION_1_TO_2
    };
}
