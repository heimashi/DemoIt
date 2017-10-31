package com.sw.rxjava.hello;


import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class Hello {

    /*
    *
    *http://gank.io/post/560e15be2dca930e00da1083
    *
    * http://blog.csdn.net/xmxkf/article/details/51612415
    *
    * */

    public void test00() {

        //被观察者
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello rxjava");
                subscriber.onCompleted();
            }
        });

        //观察者
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e);
            }

            @Override
            public void onNext(String name) {
                System.out.println(name);
            }

        };

        //订阅
        observable.subscribe(subscriber);
    }

    public void test01() {
        final String[] names = {"aaa", "bbb", "ddddd", "effs"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext(names[0]);
                subscriber.onNext(names[1]);
                subscriber.onNext(names[2]);
                subscriber.onNext(names[3]);
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("onError:" + e);
            }

            @Override
            public void onNext(String name) {
                System.out.println(name);
            }

        });
    }

    public void test02() {
        String[] names = {"aaa", "bbb", "ddddd", "effs"};
        Observable.from(names)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e);
                    }

                    @Override
                    public void onNext(String name) {
                        System.out.println(name);
                    }

                });
    }

    public void test03() {
        String[] names = {"aaa", "bbb", "ddddd", "effs"};
        Observable.from(names)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        System.out.println(name);
                    }
                });
    }

    public void test04() {
        final String[] names = {"aaa", "bbb", "ddddd", "effs"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println("Observable:" + Thread.currentThread().getName());
                subscriber.onNext(names[0]);
                subscriber.onNext(names[1]);
                subscriber.onNext(names[2]);
                subscriber.onNext(names[3]);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Subscriber:completed:" + Thread.currentThread().getName());
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError:" + e);
                    }

                    @Override
                    public void onNext(String name) {
                        System.out.println("Subscriber:" + Thread.currentThread().getName());
                        System.out.println(name);
                    }

                });
    }


}
