package com.csc.cscany.rxjava_demo.dagger_demo;

import javax.inject.Inject;

public class Delhi {

    private NewDelhi newDelhi;

    @Inject
    Delhi(NewDelhi newDelhi){
        this.newDelhi = newDelhi;
    }
}
