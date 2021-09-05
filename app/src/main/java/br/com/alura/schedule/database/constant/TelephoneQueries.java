package br.com.alura.schedule.database.constant;

public interface TelephoneQueries {
    String QUERY_GET_TELEPHONE_PART_1 = "SELECT tel.* FROM Telephone tel JOIN Student st ON tel.studentId = st.id WHERE tel.studentId =";
    String QUERY_GET_TELEPHONE_PART_2 = "LIMIT 1";
}
