package com.csc.cscany.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.csc.cscany.classes.AppDatabase;
import com.csc.cscany.classes.Repository;
import com.csc.cscany.response_models.ApiResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private Repository repository;
    private AppDatabase appDatabase;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    MainViewModel(Repository repository,AppDatabase appDatabase){
       this.repository = repository;
       this.appDatabase = appDatabase;
    }

    public MutableLiveData<ApiResponse> loginResponse() {
        return responseLiveData;
    }

    public void hitLoginApi(String name, String mobile) {

        disposables.add(repository.executeLogin(name, mobile)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }


    @Override
    protected void onCleared() {
        disposables.clear();
    }


}
