package com.sw.rxjava2_demo;


import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestBackPressure {


    private void log(String msg) {
        System.out.println(msg);
    }

    public void test1() {
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        })
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        log(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        log(throwable.toString());
                    }
                });
    }


    public void test2() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; ; i++) {  //无限循环发送事件
                    emitter.onNext(i);
                }
            }
        }).subscribeOn(Schedulers.io())
                .sample(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        log(integer.toString());
                    }
                });

    }

    public void test3() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                log("emit 1");
                emitter.onNext(1);
                log("emit 2");
                emitter.onNext(2);
                log("emit 3");
                emitter.onNext(3);
                log("emit complete");
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                log("subscribe");
            }

            @Override
            public void onNext(Integer value) {
                log("onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                log("onError");
            }

            @Override
            public void onComplete() {
                log("onComplete");
            }
        });

    }

    public void test4() {
        Flowable<Integer> upstream = Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                log("emit 1");
                emitter.onNext(1);
                log("emit 2");
                emitter.onNext(2);
                log("emit 3");
                emitter.onNext(3);
                log("emit complete");
                emitter.onComplete();
            }
        }, BackpressureStrategy.ERROR); //增加了一个参数

        Subscriber<Integer> downstream = new Subscriber<Integer>() {

            @Override
            public void onSubscribe(Subscription s) {
                log("onSubscribe");
                s.request(Long.MAX_VALUE);  //注意这句代码
            }

            @Override
            public void onNext(Integer integer) {
                log("onNext: " + integer);

            }

            @Override
            public void onError(Throwable t) {
                log("onError: " + t);
            }

            @Override
            public void onComplete() {
                log("onComplete");
            }
        };

        upstream.subscribe(downstream);
    }

    Subscription mSubscription;

    public void test5() {

        Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 128; i++) {
                    log("emit " + i);
                    emitter.onNext(i);
                }
            }
        }, BackpressureStrategy.ERROR).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        log("onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log("onError: " + t);
                    }

                    @Override
                    public void onComplete() {
                        log("onComplete");
                    }
                });
    }

    public void request() {
        mSubscription.request(96); //请求96个事件
    }

    public void test6() {
        Flowable
                .create(new FlowableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                        log("First requested = " + emitter.requested());
                        boolean flag;
                        for (int i = 0; ; i++) {
                            flag = false;
                            while (emitter.requested() == 0) {
                                if (!flag) {
                                    log("Oh no! I can't emit value!");
                                    flag = true;
                                }
                            }
                            emitter.onNext(i);
                            log("emit " + i + " , requested = " + emitter.requested());
                        }
                    }
                }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Integer>() {

                    @Override
                    public void onSubscribe(Subscription s) {
                        log("onSubscribe");
                        mSubscription = s;
                    }

                    @Override
                    public void onNext(Integer integer) {
                        log("onNext: " + integer);
                    }

                    @Override
                    public void onError(Throwable t) {
                        log("onError: " + t);
                    }

                    @Override
                    public void onComplete() {
                        log("onComplete");
                    }
                });
    }

    public void test07(){
        Flowable.just(1, 2, 3, 4, 5)
                .subscribe(new Subscriber<Integer>() {
                    Subscription sub;
                    @Override
                    public void onSubscribe(Subscription s) {
                        log("onsubscribe start");
                        sub=s;
                        sub.request(1);
                        log("onsubscribe end");
                    }
                    @Override
                    public void onNext(Integer o) {
                        log("onNext--->"+o);
                        sub.request(1);
                    }
                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }
                    @Override
                    public void onComplete() {
                        log("onComplete");
                    }
                });
    }


}
