package com.csc.cscany.view_models;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.csc.cscany.classes.AppDatabase;
import com.csc.cscany.classes.Repository;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Repository repository;
    private AppDatabase appDatabase;

    @Inject
    public ViewModelFactory(Repository repository, AppDatabase appDatabase){

        this.repository = repository;
        this.appDatabase = appDatabase;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel(repository,appDatabase);
    }

}
