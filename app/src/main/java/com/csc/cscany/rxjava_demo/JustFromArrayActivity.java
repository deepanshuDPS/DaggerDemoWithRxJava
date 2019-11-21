package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.csc.cscany.R;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class JustFromArrayActivity extends AppCompatActivity {

    String message = "Hello From RxJava";
    String[] array = {"Hello A","Hello B","Hello C"};
    Observable<String> myObservable;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //myObservable = Observable.just(message); // just asigned obsevable as string objects
        //myObservable = Observable.just(array); // just assigned observable as array string
        //myObservable = Observable.just("Hello A","Hello B","Hello C");
        // just assigned observable as string but emission data will be emitted 3 times
        myObservable = Observable.fromArray(array);// emission occurs array.length times
        // emission object type is array element type

       compositeDisposable.add(myObservable.
                subscribeOn(Schedulers.io()). // limitless thread pool
                observeOn(AndroidSchedulers.mainThread()). // on UI thread
                subscribeWith(getObserver())); // subscribe with observer
    }

    private DisposableObserver<String> getObserver(){
        return new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {

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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
