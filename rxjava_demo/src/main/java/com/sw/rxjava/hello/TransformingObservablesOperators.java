package com.sw.rxjava.hello;


import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.GroupedObservable;

/**
 * Created by shiwang on 30/10/2017.
 */

public class TransformingObservablesOperators {

    /*
    * http://reactivex.io/documentation/operators.html
    *
Transforming Observables
Operators that transform items that are emitted by an Observable.

Buffer — periodically gather items from an Observable into bundles and emit these bundles rather than emitting the items one at a time
FlatMap — transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable
GroupBy — divide an Observable into a set of Observables that each emit a different group of items from the original Observable, organized by key
Map — transform the items emitted by an Observable by applying a function to each item
Scan — apply a function to each item emitted by an Observable, sequentially, and emit each successive value
Window — periodically subdivide items from an Observable into Observable windows and emit these windows rather than emitting the items one at a time
    *
    * */

    public void testMap() {
        Observable.from(new String[]{"33", "12", "1", "100"})
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer s) {
                        print("s++:" + (++s));
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        print("error:" + throwable.toString());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        print("Complete");
                    }
                });
    }

    public void testFlatMap() {
        Observable.just("123-423-43-355-0045")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        return Observable.from(s.split("-"));
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        print(s);
                    }
                });

    }

    public void testFlatMap2() {
        Observable.just("123-423-43-355-0045","00-123","11-223-2")
                .flatMap(new Func1<String, Observable<String>>() {
                    @Override
                    public Observable<String> call(String s) {
                        print("s:"+s);
                        return Observable.from(s.split("-"));
                    }
                })
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        print(s);
                    }
                });

    }


    public void testGroupBy() {
        Observable.range(0, 11)
                .groupBy(new Func1<Integer, Integer>() {
                    @Override
                    public Integer call(Integer integer) {
                        return integer % 3;
                    }
                })
                .subscribe(new Subscriber<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) {
                        integerIntegerGroupedObservable.subscribe(new Action1<Integer>() {
                            @Override
                            public void call(Integer integer) {
                                print(integerIntegerGroupedObservable.getKey() + " " + integer);
                            }
                        });
                    }
                });


    }


    public void testBuffer() {
        Observable.range(0, 11).buffer(3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                print(integers.toString());
            }
        });
    }

    public void testBuffer2() {
        Observable.range(0, 11).buffer(3, 1).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                print(integers.toString());
            }
        });
    }

    public void testScan() {
        Observable.range(0, 5).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                return integer * integer2;
            }
        }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                print(integer.toString());
            }
        });
    }

    public void testWindow() {
        Observable.range(0, 11).window(3).subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(final Observable<Integer> integerObservable) {
                integerObservable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        print(integer + "  observable:" + integerObservable);
                    }
                });
            }
        });
    }


    private void print(String str) {
        System.out.println(str);
    }

}
