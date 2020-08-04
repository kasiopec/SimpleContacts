package com.kasiopec.contactsprofile.database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import io.reactivex.Flowable;

@Dao
public interface UserDataDAO {
    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUser(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAllUsers(List<User> userList);

    @Delete
    void deleteUser(User user);
}
