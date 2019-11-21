package com.csc.cscany.rxjava_demo.dagger_demo;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class BandraMumbaiModule {

    // because we inject BandraMumbai constructor
    // here we binds
    /*@Provides
    Mumbai providesBandraMumbai(BandraMumbai bandraMumbai){
        bandraMumbai.showCity();
        return bandraMumbai;
    }*/

    @Binds
    abstract Mumbai bindsBandraMumbai(BandraMumbai bandraMumbai);
}
