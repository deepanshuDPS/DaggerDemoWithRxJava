package com.csc.cscany.response_models;

import androidx.annotation.NonNull;

import com.google.gson.JsonElement;

import io.reactivex.annotations.Nullable;

public class ApiResponse {

    private final Status status;

    @Nullable
    private final Object data;

    @Nullable
    private final Throwable error;

    private ApiResponse(Status status, @Nullable Object data, @Nullable Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static ApiResponse loading() {
        return new ApiResponse(Status.LOADING, null, null);
    }

    public static ApiResponse success(@NonNull Object data) {
        return new ApiResponse(Status.SUCCESS, data, null);
    }

    public static ApiResponse error(@NonNull Throwable error) {
        return new ApiResponse(Status.ERROR, null, error);
    }

    public enum Status {
        LOADING,
        SUCCESS,
        ERROR,
        COMPLETED
    }

    public Object getData() {
        return data;
    }

    public Status getStatus(){
        return status;
    }
}
