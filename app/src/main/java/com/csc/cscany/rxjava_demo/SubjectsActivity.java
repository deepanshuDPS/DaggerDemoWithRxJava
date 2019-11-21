package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.csc.cscany.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

public class SubjectsActivity extends AppCompatActivity {

    private String[] array = {"JAVA","KOTLIN","XML","JSON"};
    private Observable<String> myObservable;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);


        //asyncSubject2();
        //bevSubject2();
        //publishSubject2();
        replaySubject2();

    }

    private void bevSubject1(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
        myObservable = Observable.fromArray(array);
        myObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(behaviorSubject);

        behaviorSubject.subscribe(getFirstObserver());
        behaviorSubject.subscribe(getSecondObserver());
        behaviorSubject.subscribe(getThirdObserver());
    }


    private void bevSubject2(){

        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();



        behaviorSubject.subscribe(getFirstObserver());

        behaviorSubject.onNext("JAVA");
        behaviorSubject.onNext("KOLTIN");
        behaviorSubject.onNext("XML");



        behaviorSubject.subscribe(getSecondObserver());

        behaviorSubject.onNext("JSON");
        behaviorSubject.onComplete();

        behaviorSubject.subscribe(getThirdObserver());
    }

    private void asyncSubject1(){

        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        myObservable = Observable.fromArray(array);
        myObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(asyncSubject);

        asyncSubject.subscribe(getFirstObserver());
        asyncSubject.subscribe(getSecondObserver());
        asyncSubject.subscribe(getThirdObserver());
    }


    private void asyncSubject2(){

        AsyncSubject<String> asyncSubject = AsyncSubject.create();


        asyncSubject.subscribe(getFirstObserver());


        asyncSubject.onNext("JAVA");
        asyncSubject.onNext("KOLTIN");

        asyncSubject.onNext("XML");
        asyncSubject.subscribe(getSecondObserver());


        asyncSubject.onNext("JSON");
        asyncSubject.onComplete();

        asyncSubject.subscribe(getThirdObserver());
    }

    private void publishSubject1(){

        PublishSubject<String> publishSubject = PublishSubject.create();
        myObservable = Observable.fromArray(array);
        myObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(publishSubject);

        publishSubject.subscribe(getFirstObserver());
        publishSubject.subscribe(getSecondObserver());
        publishSubject.subscribe(getThirdObserver());
    }


    private void publishSubject2(){

        PublishSubject<String> publishSubject = PublishSubject.create();


        publishSubject.subscribe(getFirstObserver());

        publishSubject.onNext("JAVA");
        publishSubject.onNext("KOLTIN");
        publishSubject.onNext("XML");


        publishSubject.subscribe(getSecondObserver());

        publishSubject.onNext("JSON");
        publishSubject.onComplete();


        publishSubject.subscribe(getThirdObserver());
    }

    private void replaySubject1(){

        ReplaySubject<String> replaySubject = ReplaySubject.create();
        myObservable = Observable.fromArray(array);
        myObservable.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(replaySubject);

        replaySubject.subscribe(getFirstObserver());
        replaySubject.subscribe(getSecondObserver());
        replaySubject.subscribe(getThirdObserver());
    }


    private void replaySubject2(){

        ReplaySubject<String> replaySubject = ReplaySubject.create();

        replaySubject.subscribe(getFirstObserver());
        replaySubject.onNext("JAVA");
        replaySubject.onNext("KOLTIN");

        replaySubject.onNext("XML");

        replaySubject.subscribe(getSecondObserver());

        replaySubject.onNext("JSON");
        replaySubject.onComplete();

        replaySubject.subscribe(getThirdObserver());
    }

    private Observer<String> getFirstObserver(){

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("rxjavatag","First Subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("rxjavatag","First Emits: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rxjavatag","First Error");
            }

            @Override
            public void onComplete() {
                Log.i("rxjavatag","First Complete");
            }
        };
    }

    private Observer<String> getSecondObserver(){

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("rxjavatag","Second Subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("rxjavatag","Second Emits: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rxjavatag","Second Error");
            }

            @Override
            public void onComplete() {
                Log.i("rxjavatag","Second Complete");
            }
        };
    }

    private Observer<String> getThirdObserver(){

        return new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i("rxjavatag","Third Subscribe");
            }

            @Override
            public void onNext(String s) {
                Log.i("rxjavatag","Third Emits: "+s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i("rxjavatag","Third Error");
            }

            @Override
            public void onComplete() {
                Log.i("rxjavatag","Third Complete");
            }
        };
    }
}
