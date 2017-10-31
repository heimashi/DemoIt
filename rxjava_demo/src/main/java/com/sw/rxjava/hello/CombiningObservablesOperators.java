package com.sw.rxjava.hello;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by shiwang on 31/10/2017.
 */

public class CombiningObservablesOperators {

    /*
    * http://reactivex.io/documentation/operators.html
    *
   Combining Observables
Operators that work with multiple source Observables to create a single Observable

And/Then/When — combine sets of items emitted by two or more Observables by means of Pattern and Plan intermediaries
CombineLatest — when an item is emitted by either of two Observables, combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function
Join — combine items emitted by two Observables whenever an item from one Observable is emitted during a time window defined according to an item emitted by the other Observable
Merge — combine multiple Observables into one by merging their emissions
StartWith — emit a specified sequence of items before beginning to emit the items from the source Observable
Switch — convert an Observable that emits Observables into a single Observable that emits the items emitted by the most-recently-emitted of those Observables
Zip — combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function
    *
    *
    * http://blog.csdn.net/xmxkf/article/details/51656736
    *
    *
    * */


    //创建不同名称的Observable（每隔100ms发射一个数据 ）：
    private Observable<String> getObservable(final String name) {
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (name.contains("-")) {
                    for (int i = 1; i <= 3; i++) {
                        Log.v(TAG, name + i);
                        subscriber.onNext(name + i);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                }
            }
        }).subscribeOn(Schedulers.newThread());
    }

    public void testCombineLast() {
        Observable.combineLatest(getObservable("one->"), getObservable("two->"), getObservable("three->"),
                new Func3<String, String, String, String>() {
                    //使用一个函数结合它们最近发射的数据，然后发射这个函数的返回值
                    @Override
                    public String call(String str1, String str2, String str3) {
                        return str1 + "," + str2 + "," + str3;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.v(TAG, "combineLatest:" + s);
            }
        });
    }


}
