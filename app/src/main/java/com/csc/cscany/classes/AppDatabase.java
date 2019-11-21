package com.csc.cscany.classes;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.csc.cscany.response_models.LogInResponse;

@Database(entities = {LogInResponse.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // define daos here
}
