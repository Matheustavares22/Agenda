package br.com.alura.schedule.database.dao;

import static br.com.alura.schedule.database.constant.TelephoneQueries.QUERY_GET_ALL_TELEPHONE;
import static br.com.alura.schedule.database.constant.TelephoneQueries.QUERY_GET_FIRST_TELEPHONE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import br.com.alura.schedule.model.Telephone;

@Dao
public interface TelephoneDAO {

    @Query(QUERY_GET_FIRST_TELEPHONE)
    Telephone findFirstTelephone(int studentId);

    @Insert
    void save(Telephone... telephones);

    @Query(QUERY_GET_ALL_TELEPHONE)
    List<Telephone> findAllTelephones(int studentId);
}
