package com.sw.rxjava.hello;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func0;

/**
 * Created by shiwang on 30/10/2017.
 */

public class CreatingObservablesOperators {


    public void testCreate() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> observer) {
                try {
                    if (!observer.isUnsubscribed()) {
                        for (int i = 1; i < 5; i++) {
                            observer.onNext(i);
                        }
                        observer.onCompleted();
                    }
                } catch (Exception e) {
                    observer.onError(e);
                }
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onNext(Integer item) {
                System.out.println("onNext: " + item);
            }

            @Override
            public void onError(Throwable error) {
                System.err.println("onError: " + error.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }
        });
    }


    public void testJust() {
        Observable.just(1, 2, 3)
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer item) {
                        System.out.println("onNext: " + item);
                    }

                    @Override
                    public void onError(Throwable error) {
                        System.err.println("onError: " + error.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }
                });
    }


    public void testFrom() {
        Integer[] items = {0, 1, 2, 3, 4, 5};
        Observable<Integer> myObservable = Observable.from(items);

        myObservable.subscribe(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer item) {
                        System.out.println(item);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable error) {
                        System.out.println("Error encountered: " + error.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        System.out.println("Sequence complete");
                    }
                }
        );
    }


    public class SomeType {
        private String value;

        public void setValue(String value) {
            this.value = value;
        }

        public Observable<String> valueObservable() {
            return Observable.just(value);
        }

        public Observable<String> valueCreateObservable() {
            return Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    subscriber.onNext(value);
                    subscriber.onCompleted();
                }
            });
        }

        public Observable<String> valueDeferObservable() {
            return Observable.defer(new Func0<Observable<String>>() {
                @Override public Observable<String> call() {
                    return Observable.just(value);
                }
            });
        }


    }


    public void testDefer1() {
        SomeType instance = new SomeType();
        Observable<String> value = instance.valueObservable();
        instance.setValue("Some Value");
        value.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s:" + s);
            }
        });
    }


    public void testDefer2() {
        SomeType instance = new SomeType();
        Observable<String> value = instance.valueDeferObservable();
        instance.setValue("Some Value");
        value.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("s:" + s);
            }
        });
    }


    public void testRange(){
        Observable<Integer>
    }



}
