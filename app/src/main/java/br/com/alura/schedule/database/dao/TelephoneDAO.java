package br.com.alura.schedule.database.dao;

import static br.com.alura.schedule.database.constant.TelephoneQueries.QUERY_GET_TELEPHONE_PART_1;
import static br.com.alura.schedule.database.constant.TelephoneQueries.QUERY_GET_TELEPHONE_PART_2;

import androidx.room.Dao;
import androidx.room.Query;

import br.com.alura.schedule.model.Telephone;

@Dao
public interface TelephoneDAO {

    @Query(QUERY_GET_TELEPHONE_PART_1 + " :studentId " + QUERY_GET_TELEPHONE_PART_2)
    Telephone findFirstTelephone(int studentId);
//
//    @Insert
//    public abstract void save(Telephone telephone);
//
//    @Delete
//    public abstract void remove(Telephone telephone);
//
//    @Query("SELECT * FROM Telephone")
//    public abstract List<Telephone> all();
//
//    @Update
//    public abstract void edit(Telephone telephone);

}
