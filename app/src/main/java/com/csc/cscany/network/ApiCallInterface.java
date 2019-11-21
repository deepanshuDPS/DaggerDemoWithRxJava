package com.csc.cscany.network;

import com.csc.cscany.response_models.LogInResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiCallInterface {

    @FormUrlEncoded
    @POST("api/v1/auth/signup/")
    Observable<LogInResponse> login(@Field("name") String name, @Field("mobile") String mobile);


}
