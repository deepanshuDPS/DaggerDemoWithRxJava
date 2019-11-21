package com.csc.cscany.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.csc.cscany.application.MyApplication;
import com.csc.cscany.network.interceptor.NormalApiInterceptor;
import com.csc.cscany.view_models.ViewModelFactory;

import javax.inject.Inject;

public abstract class BaseActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    @Inject
    NormalApiInterceptor apiInterceptor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().getAppComponent().inject(this);
    }



}
