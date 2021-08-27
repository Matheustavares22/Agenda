package br.com.alura.schedule.database;

public interface ConstantDatabase {
    String DATABASE_NAME = "schedule.db";
    String QUERY_MIGRATION_V1_TO_V2 = "ALTER TABLE student ADD surname CHAR(50)";
    String QUERY_MIGRATION_V2_TO_V3_STEP_ONE = "CREATE TABLE IF NOT EXISTS `newStudent` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `telephone` TEXT, `email` TEXT)";
    String QUERY_MIGRATION_V2_TO_V3_STEP_TWO = "INSERT INTO newStudent(id, NAME, telephone, email) SELECT id, NAME, telephone, email FROM student";
    String QUERY_MIGRATION_V2_TO_V3_STEP_THREE = "DROP TABLE student";
    String QUERY_MIGRATION_V2_TO_V3_STEP_FOUR = "ALTER TABLE newStudent RENAME TO student";
}
