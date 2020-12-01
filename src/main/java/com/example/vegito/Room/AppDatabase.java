package com.example.vegito.Room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.vegito.Models.CreateAccount.ResultCreateAccount;
import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Models.LoginModel.Result;

@Database(entities = {ResponseCreateCart.class, Result.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract accountInfoDao accountInfoDao();
}
