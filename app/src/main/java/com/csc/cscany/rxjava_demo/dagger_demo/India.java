package com.csc.cscany.rxjava_demo.dagger_demo;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

// singleton because we are using the class in many activities
@Singleton
public class India {

    private Delhi delhi;
    private Mumbai mumbai;
    private Punjab punjab;

    @Inject
    public India(Delhi delhi, Mumbai mumbai, Punjab punjab) {
        this.delhi = delhi;
        this.mumbai = mumbai;
        this.punjab = punjab;
    }

    public void cities(){
        Log.i("Cities","There are 3 cities that have one Union Territory");
    }
}
