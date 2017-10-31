package com.sw.rxjava.hello;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by shiwang on 31/10/2017.
 */

public class FilteringObservablesOperators {


    /*
    * http://reactivex.io/documentation/operators.html
    *
    *
   Filtering Observables
Operators that selectively emit items from a source Observable.

Debounce — only emit an item from an Observable if a particular timespan has passed without it emitting another item
Distinct — suppress duplicate items emitted by an Observable
ElementAt — emit only item n emitted by an Observable
Filter — emit only those items from an Observable that pass a predicate test
First — emit only the first item, or the first item that meets a condition, from an Observable
IgnoreElements — do not emit any items from an Observable but mirror its termination notification
Last — emit only the last item emitted by an Observable
Sample — emit the most recent item emitted by an Observable within periodic time intervals
Skip — suppress the first n items emitted by an Observable
SkipLast — suppress the last n items emitted by an Observable
Take — emit only the first n items emitted by an Observable
TakeLast — emit only the last n items emitted by an Observable
    *
    * */


    public void testDebounce() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                        try {
                            Thread.sleep(i * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .debounce(5, TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print("call():" + integer);
                    }
                });
    }


    public void testFilter() {
        Observable.range(0, 10)
                .filter(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 3 == 0;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }


    public void testSkip() {
        Observable.range(0, 10)
                .skip(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }

    public void testSkip2() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                try {
                    for (int i = 0; i < 10; i++) {
                        subscriber.onNext(i);
                        try {
                            Thread.sleep(i * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .skip(5, TimeUnit.SECONDS)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print("call():" + integer);
                    }
                });
    }

    public void testSkipLast() {
        Observable.range(0, 10)
                .skipLast(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }


    public void testTake() {
        Observable.range(0, 10)
                .take(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }

    public void testTakeLast() {
        Observable.range(0, 10)
                .takeLast(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }


    public void testElementAt() {
        Observable.range(0, 10)
                .elementAt(4)
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }

    public void testFirst() {
        Observable.range(0, 10)
                .first()
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }

    public void testFirst2() {
        Observable.range(0, 10)
                .first(new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer % 3 == 2;
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer.toString());
                    }
                });
    }


    private void print(String str) {
        System.out.println(str);
    }

}
