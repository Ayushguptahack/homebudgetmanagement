package com.example.homebudgetusingroomdatabase.Room;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {expense.class}, version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase{

    public abstract DAO dao();
}
