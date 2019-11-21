package com.csc.cscany.classes;

import com.csc.cscany.network.ApiCallInterface;
import com.csc.cscany.response_models.LogInResponse;

import io.reactivex.Observable;

public class Repository {

    private ApiCallInterface apiCallInterface;

    public Repository(ApiCallInterface apiCallInterface) {
        this.apiCallInterface = apiCallInterface;
    }

    /*
     * method to call login api
     * */
    public Observable<LogInResponse> executeLogin(String name, String mobileNumber) {
        return apiCallInterface.login(name,mobileNumber);
    }

}