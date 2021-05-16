package com.example.roomcustom;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {DiaChi.class},version = 1)
public abstract  class ConnectDB extends RoomDatabase {
    private static  final String DATABASE_NAME = "DiaChi.db";
    private static  ConnectDB instance;
    public static  synchronized ConnectDB getInstance(MainActivity context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ConnectDB.class,DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public static ConnectDB getInstance() {
        return null;
    }

    public abstract DiaChiDAO diaChiDAO();
}