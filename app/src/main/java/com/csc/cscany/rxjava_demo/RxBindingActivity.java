package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.csc.cscany.R;
import com.jakewharton.rxbinding3.view.RxView;
import com.jakewharton.rxbinding3.widget.RxTextView;

import io.reactivex.functions.Consumer;
import kotlin.Unit;

public class RxBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding);
        final TextView tv = findViewById(R.id.tv);
        Button bt = findViewById(R.id.bt);
        final EditText et = findViewById(R.id.et);

        RxTextView.textChanges(et).
                subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) {
                        tv.setText(et.getText().toString());
                    }
                });
        RxView.clicks(bt).
                subscribe(new Consumer<Unit>() {
                    @Override
                    public void accept(Unit unit)  {
                        tv.setText("");et.setText("");
                    }
                });
    }
}
