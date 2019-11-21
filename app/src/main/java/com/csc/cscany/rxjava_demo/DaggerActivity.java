package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.csc.cscany.R;
import com.csc.cscany.rxjava_demo.dagger_demo.Delhi;
import com.csc.cscany.rxjava_demo.dagger_demo.India;
import com.csc.cscany.rxjava_demo.dagger_demo.Mumbai;

import javax.inject.Inject;

public class DaggerActivity extends AppCompatActivity {

    // field injections
    @Inject
    India myIndia;

    @Inject
    Delhi delhi;

    @Inject
    Mumbai mumbai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);

        // creating india components dependency after rebuild with java generated code
        //IndiaComponents indiaComponents = DaggerIndiaComponents.create();
        // now make instance/object of India

        /*IndiaComponents indiaComponents = DaggerIndiaComponents.builder()
                .punjabModule(new PunjabModule(100000)).build();

        // field injection
        indiaComponents.inject(this);*/

        //App.getApp().getIndiaComponents().inject(this);

        //myIndia = indiaComponents.getIndiaCities();
        myIndia.cities();
    }
}
