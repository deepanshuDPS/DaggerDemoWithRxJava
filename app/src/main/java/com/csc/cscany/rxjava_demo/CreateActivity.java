package com.csc.cscany.rxjava_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.csc.cscany.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CreateActivity extends AppCompatActivity {

    private Observable<Student> myObservable;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        myObservable = Observable.create(new ObservableOnSubscribe<Student>() {
            @Override
            public void subscribe(ObservableEmitter<Student> emitter)  {

                for(Student i:getStudents()){
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
        });

             compositeDisposable.add(myObservable.
                subscribeOn(Schedulers.io()).
                /*map(new Function<Student, Student>() {


                    @Override
                    public Student apply(Student student) {

                        if(student.marks<50)
                            student.setName(student.getName().toUpperCase());
                        else
                            student.setName(student.getName().toLowerCase());

                        return student;
                    }
                })*/
                /*concatMap(new Function<Student, ObservableSource<Student>>() {
                    @Override
                    public ObservableSource<Student> apply(Student student) throws Exception {

                        if(student.getName().contains("Raju"))
                            return Observable.just(student,new Student("Rastogi","r@gmail.com",23));
                        return Observable.just(student);
                    }
                }).*/
                //buffer(3). // emits the list not a single student so change observer object to List
                /*filter(new Predicate<Student>() {
                    @Override
                    public boolean test(Student student) {
                        return student.getMarks()>50;
                    }
                }).*/
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(getObserver()));
    }

    private DisposableObserver<Student> getObserver(){
        return new DisposableObserver<Student>() {
            @Override
            public void onNext(Student s) {

                Log.i("RXjavaTag","onNext emitted data is: "+s.getName());
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

    private List<Student> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student("Deepanshu","deepanshu@gmail.com",100));
        students.add(new Student("Deepu","deepu@gmail.com",43));
        students.add(new Student("DPS","dps@gmail.com",43));
        students.add(new Student("Raju","raju@gmail.com",43));
        students.add(new Student("Abhishek","abhishek@gmail.com",100));
        return students;
    }

    class Student{

        private String name,email;
        private int marks;

        public Student(String name, String email, int marks) {
            this.name = name;
            this.email = email;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getMarks() {
            return marks;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}
