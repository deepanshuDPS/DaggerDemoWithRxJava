package com.csc.cscany.modules;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import com.csc.cscany.classes.AppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    @Singleton
    @Provides
    AppDatabase providesAppDatabase(Application application){
        Log.i("dagger","creating database");
        return Room.databaseBuilder(application.getApplicationContext(),
                AppDatabase.class, "db_name").build();
    }
}
