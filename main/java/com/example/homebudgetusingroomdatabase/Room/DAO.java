package com.example.homebudgetusingroomdatabase.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAO {

    @Insert
    public void insertData(expense exp);

    @Query("Select * from expense")
    List<expense> getUser();

    @Query("Update expense set  value= :newval where description = :desc")
    void updateUser(String desc,String newval);

    @Query("Delete from expense where description = :desc")
    void deletedata(String desc);
}
