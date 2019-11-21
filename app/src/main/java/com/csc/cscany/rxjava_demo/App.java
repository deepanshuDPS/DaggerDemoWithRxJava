package com.csc.cscany.rxjava_demo;

import android.app.Application;

import com.csc.cscany.rxjava_demo.dagger_demo.DaggerIndiaComponents;
import com.csc.cscany.rxjava_demo.dagger_demo.IndiaComponents;
import com.csc.cscany.rxjava_demo.dagger_demo.PunjabModule;


public class App extends Application {

    private static App app;
    private IndiaComponents indiaComponents;

    public static App getApp(){
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        indiaComponents = DaggerIndiaComponents.builder()
                .punjabModule(new PunjabModule(100000)).build();
    }

    public IndiaComponents getIndiaComponents() {
        return indiaComponents;
    }
}
