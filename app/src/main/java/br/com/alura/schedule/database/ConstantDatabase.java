package br.com.alura.schedule.database;

public interface ConstantDatabase {
    String DATABASE_NAME = "schedule.db";
    String QUERY_MIGRATION_V1_TO_V2 = "ALTER TABLE student ADD surname CHAR(50)";
}
