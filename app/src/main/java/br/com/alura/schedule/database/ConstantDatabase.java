package br.com.alura.schedule.database;

public interface ConstantDatabase {
    String DATABASE_NAME = "schedule.db";
    String QUERY_MIGRATION_1_TO_2 = "ALTER TABLE student ADD surname TEXT";
    String QUERY_MIGRATION_2_TO_3_STEP_ONE = "CREATE TABLE IF NOT EXISTS newStudent (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT, telephone TEXT, email TEXT)";
    String QUERY_MIGRATION_2_TO_3_STEP_TWO = "INSERT INTO newStudent(id, NAME, telephone, email) SELECT id, NAME, telephone, email FROM student";
    String QUERY_MIGRATION_2_TO_3_STEP_THREE = "DROP TABLE student";
    String QUERY_MIGRATION_2_TO_3_STEP_FOUR = "ALTER TABLE newStudent RENAME TO student";
    String QUERY_MIGRATION_3_TO_4 = "ALTER TABLE student ADD additionDate INTEGER";
}
