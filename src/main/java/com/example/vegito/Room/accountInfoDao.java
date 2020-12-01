package com.example.vegito.Room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.vegito.Models.CreateCart.ResponseCreateCart;
import com.example.vegito.Models.LoginModel.Result;

@Dao
public interface accountInfoDao {

//    @Query("SELECT * FROM resultcreateaccount")
//    ResultCreateAccount getAll();

    @Query("SELECT * FROM responsecreatecart")
    ResponseCreateCart getCartId();

    @Query("SELECT * FROM result")
    Result getAll();


    @Insert
    void insert(ResponseCreateCart responsecreatecart);

    @Insert
    void insert(Result result);

    @Query("delete from Result")
    void delete();

    @Update
    void update(Result result);
}
