package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.csc.cscany.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class BriefActivity extends AppCompatActivity {

    String message = "Hello From RxJava";
    Observable<String> myObservable;
    DisposableObserver<String> myObserver;
    DisposableObserver<String> myObserver2;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    //Disposable myDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myObservable = Observable.just(message);
        /*myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("RXjavaTag","onSubscribe");
                //myDisposable = d;
            }

            @Override
            public void onNext(String s) {
                TextView tv = findViewById(R.id.tv_message);
                tv.setText(s);
                Log.i("RXjavaTag","onNext emitted data is: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("RXjavaTag","onError");
            }

            @Override
            public void onComplete() {
                Log.i("RXjavaTag","onComplete");
            }
        };*/

        myObserver = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                TextView tv = findViewById(R.id.tv_message);
                tv.setText(s);
                Log.i("RXjavaTag","onNext emitted data is: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("RXjavaTag","onError");
            }

            @Override
            public void onComplete() {
                Log.i("RXjavaTag","onComplete");
            }
        };
        myObserver2 = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {
                TextView tv = findViewById(R.id.tv_message);
                tv.setText(s);
                Log.i("RXjavaTag","onNext emitted data is: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("RXjavaTag","onError");
            }

            @Override
            public void onComplete() {
                Log.i("RXjavaTag","onComplete");
            }
        };
        //subscribing the observer with observable
        myObservable.subscribe(myObserver);
        myObservable.subscribe(myObserver2);
        compositeDisposable.add(myObserver);
        compositeDisposable.add(myObserver2);
        // if we have more then two observer then we have to dispose
        // one by one it increases the code length
        // use CompositeDisposable to add all disposable and dispose them all together

        // code reduction subscription in one line
        DisposableObserver dO = myObservable.
                subscribeOn(Schedulers.io()). // limitless thread pool
                observeOn(AndroidSchedulers.mainThread()). // on UI thread
                subscribeWith(myObserver); // subscribe with observer

        // return disposable observer when subscribe with and add to composite disposable
        compositeDisposable.add(dO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       // myDisposable.dispose();
        //myObserver.dispose();
        // use clear() to dispose all observer with composite diposable
        compositeDisposable.clear();
    }
}
