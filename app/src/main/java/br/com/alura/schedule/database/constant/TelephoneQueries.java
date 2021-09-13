package br.com.alura.schedule.database.constant;

public interface TelephoneQueries {
    String QUERY_GET_FIRST_TELEPHONE = "SELECT * FROM Telephone WHERE studentId = :studentId LIMIT 1";
    String QUERY_GET_ALL_TELEPHONE  = "SELECT * FROM Telephone WHERE studentId = :studentId";
}
