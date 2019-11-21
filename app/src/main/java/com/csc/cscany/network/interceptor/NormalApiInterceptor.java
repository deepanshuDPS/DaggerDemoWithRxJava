package com.csc.cscany.network.interceptor;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class NormalApiInterceptor implements Interceptor {
    private String token;

    @Inject
    NormalApiInterceptor() {
        token = "no_data";
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NotNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request compressedRequest;
        if(token.equals("no_data")){
            compressedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(request.method(), request.body())
                    .build();
        }
        else{
            compressedRequest = request.newBuilder()
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .method(request.method(), request.body())
                    .build();
        }

        return chain.proceed(compressedRequest);
    }


    public void clearToken(){
        token = "no_data";
    }
}