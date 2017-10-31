package com.sw.rxjava.hello;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
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


    public void testJoin() {
        //目标Observable
        Observable<Integer> obs1 = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 1; i < 5; i++) {
                    subscriber.onNext(i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                subscriber.onCompleted();
            }
        });
        //join
        Observable.just("srcObs-")
                .join(obs1,
                        //接受从源Observable发射来的数据，并返回一个Observable，
                        //这个Observable的生命周期决定了源Observable发射出来数据的有效期
                        new Func1<String, Observable<Long>>() {
                            @Override
                            public Observable<Long> call(String s) {
                                return Observable.timer(3000, TimeUnit.MILLISECONDS);
                            }
                        },
                        //接受从目标Observable发射来的数据，并返回一个Observable，
                        //这个Observable的生命周期决定了目标Observable发射出来数据的有效期
                        new Func1<Integer, Observable<Long>>() {
                            @Override
                            public Observable<Long> call(Integer integer) {
                                return Observable.timer(2000, TimeUnit.MILLISECONDS);
                            }
                        },
                        //接收从源Observable和目标Observable发射来的数据，并返回最终组合完的数据
                        new Func2<String, Integer, String>() {
                            @Override
                            public String call(String str1, Integer integer) {
                                return str1 + integer;
                            }
                        })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        Log.v(TAG, "join:" + o);
                    }
                });

    }


    public void testMerge() {
        Observable<Integer> odds = Observable.just(1, 3, 5);
        Observable<Integer> evens = Observable.just(2, 4, 6);
        Observable.merge(odds, evens)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        print("merge Next: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        print("merge Error: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        print("merge Sequence complete.");
                    }
                });
    }

    public void testStartWith() {
        Observable<Integer> obs1 = Observable.just(1, 2, 3);
        Observable<Integer> obs2 = Observable.just(4, 5, 6);
        obs1.startWith(obs2).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                print("onNext:" + integer);
            }
        });
    }

    public void testZip() {
        Observable obs1 = Observable.just(1, 2, 3, 4, 5);
        Observable obs2 = Observable.just(10, 20, 30, 40);
/*
 * zip(Observable,FuncN):
 * ①.能接受1~9个Observable作为参数，或者单个Observables列表作为参数；
 *    Func函数的作用就是从每个Observable中获取一个数据进行结合后发射出去；
 * ②.小Observable的每个数据只能组合一次，如果第二个小Observable发射数据的时候，
 *    第一个还没有发射，将要等待第一个发射数据后才能组合；
 */
        Observable.zip(obs1, obs2,
                new Func2<Integer, Integer, String>() {
                    //使用一个函数结合每个小Observable的一个数据（每个数据只能组合一次），然后发射这个函数的返回值
                    @Override
                    public String call(Integer int1, Integer int2) {
                        return int1 + "-" + int2;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                print("zip:" + s);
            }
        });

/*
 * zipWith(Observable,Func2):
 * ①.zipWith不是static的，必须由一个Observable对象调用
 * ②.如果要组合多个Observable，可以传递Iterable
 */
        obs1.zipWith(obs2, new Func2<Integer, Integer, String>() {
            //使用一个函数结合每个小Observable的一个数据（每个数据只能组合一次），然后发射这个函数的返回值
            @Override
            public String call(Integer int1, Integer int2) {
                return int1 + "-" + int2;
            }
        }).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                print("zipWith:" + s);
            }
        });

    }


    private void print(String str) {
        System.out.println(str);
    }


}
