package com.csc.cscany.application;

import android.app.Application;

import com.csc.cscany.components.AppComponent;
import com.csc.cscany.components.DaggerAppComponent;
import com.csc.cscany.modules.AppModule;
import com.csc.cscany.modules.DbModule;
import com.csc.cscany.modules.UtilsModules;

public class MyApplication extends Application {

    private static MyApplication myApplication = null;
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dbModule(new DbModule())
                .utilsModules(new UtilsModules())
                .build();
    }

    public static MyApplication getInstance(){
        return myApplication;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
