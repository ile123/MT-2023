package com.example.asyncroom;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM korisnik")
    List<User> getAll();

    @Query("SELECT * FROM korisnik WHERE first_name LIKE :name AND " +
    "users_occupation LIKE :occupation LIMIT 1")
    User findByName(String name, String occupation);

    @Insert
    void insertAll(User... users);

    @Delete
    void delete(User user);


}
