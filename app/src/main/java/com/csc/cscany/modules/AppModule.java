package com.csc.cscany.modules;


import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application myApplication;

    public AppModule(Application myApplication){
        this.myApplication = myApplication;
    }
    @Provides
    @Singleton
    Application providesApplication(){
        return myApplication;
    }

}
