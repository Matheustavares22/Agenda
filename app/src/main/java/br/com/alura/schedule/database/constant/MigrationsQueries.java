package br.com.alura.schedule.database.constant;

public interface MigrationsQueries {
    String MIGRATION_1_TO_2_STEP_ONE = "CREATE TABLE IF NOT EXISTS `Student_new` (" +
            "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " `name` TEXT," +
            " `email` TEXT)";
    String MIGRATION_1_TO_2_STEP_TWO = "INSERT INTO Student_new(id, name, email)  SELECT id, name, email FROM Student";
    String MIGRATION_1_TO_2_STEP_THREE = "CREATE TABLE IF NOT EXISTS `Telephone` (" +
            " `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            " `number` TEXT," +
            " `type` TEXT," +
            " `studentId` INTEGER NOT NULL," +
            " FOREIGN KEY(`studentId`) REFERENCES `Student`(`id`) ON UPDATE CASCADE ON DELETE CASCADE )";
    String MIGRATION_1_TO_2_STEP_FOUR = "INSERT INTO Telephone(number, studentId)  SELECT cellphone, id FROM Student";
    String MIGRATION_1_TO_2_STEP_FIVE = "UPDATE Telephone SET type =";
    String MIGRATION_1_TO_2_STEP_SIX = "DROP TABLE Student";
    String MIGRATION_1_TO_2_STEP_SEVEN = "ALTER TABLE Student_new RENAME TO Student";
}
