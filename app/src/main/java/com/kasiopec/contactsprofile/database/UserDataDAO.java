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
    /**
     * Gets all the users from the database and wraps in flowable so we can observe them later on
     * **/
    @Query("SELECT * FROM users")
    Flowable<List<User>> getAllUsers();
    /**
     * Adds a user into a DB and if the user exists, replaces with the new values
     * Update & insert in one method
     * **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertUser(User user);

    /**
     * Adds a list of users into a DB and if the users exist, replaces them with the new values
     * Update & insert in one method
     * **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void upsertAllUsers(List<User> userList);

    /**
     *Deletes a user from a DB
     * **/
    @Delete
    void deleteUser(User user);
}
