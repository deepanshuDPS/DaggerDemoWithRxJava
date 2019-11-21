package com.csc.cscany.activities;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.csc.cscany.R;
import com.csc.cscany.response_models.ApiResponse;
import com.csc.cscany.response_models.LogInResponse;
import com.csc.cscany.view_models.MainViewModel;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainViewModel viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel.class);
        // viewModel.hitLoginApi("Deepanshu","8800757476");
        // hitting api
        viewModel.loginResponse().observe(this, this::consumeResponse);
    }

    /*
     * method to handle response
     * */
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.getStatus()) {

            case LOADING:
                break;

            case SUCCESS:
                // here we get all data
                LogInResponse logInResponse= (LogInResponse) apiResponse.getData();
                break;

            case ERROR:
                break;

            default:
                break;
        }
    }
}
