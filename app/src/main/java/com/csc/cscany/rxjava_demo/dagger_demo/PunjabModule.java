package com.csc.cscany.rxjava_demo.dagger_demo;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

@Module
public class PunjabModule {

    private int population;

    public PunjabModule(int population){
        this.population = population;
    }

    // if we make provider method as static then it does not
    // depend on the instance variable of the module
    // dagger will never need to instantiate them
    // it will improve performance
    @Provides
    Punjab providePunjab(){
        Log.i("Punjab Population","P="+population);
        return new Punjab();
    }
}
