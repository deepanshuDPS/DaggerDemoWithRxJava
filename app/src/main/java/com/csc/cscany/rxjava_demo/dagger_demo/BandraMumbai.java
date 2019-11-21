package com.csc.cscany.rxjava_demo.dagger_demo;

import android.util.Log;

import javax.inject.Inject;

public class BandraMumbai implements Mumbai {


    @Inject
    BandraMumbai(){
    }

    @Override
    public void showCity() {
        Log.i("mumbai","i am in bandara");
    }
}
