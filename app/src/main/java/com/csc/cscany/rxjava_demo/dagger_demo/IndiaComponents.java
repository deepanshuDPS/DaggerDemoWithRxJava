package com.csc.cscany.rxjava_demo.dagger_demo;


import com.csc.cscany.rxjava_demo.DaggerActivity;

import javax.inject.Singleton;

import dagger.Component;


// singleton because we are using the components in many activities
@Singleton
@Component(modules ={BandraMumbaiModule.class, PunjabModule.class})
public interface IndiaComponents {


    //India getIndiaCities();

    // for field injections
    void inject(DaggerActivity activity);
}
