package com.sw.rxjava.hello;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by shiwang on 31/10/2017.
 */

public class ErrorHandlingOperators {


    /*
    *
    * Error Handling Operators
Operators that help to recover from error notifications from an Observable

Catch — recover from an onError notification by continuing the sequence without error
Retry — if a source Observable sends an onError notification, resubscribe to it in the hopes that it will complete without error
    * */


    public void testError() {

        print("-------------------------Throwable");

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //会忽略onError调用，不会将错误传递给观察者
                        subscriber.onError(new Throwable("i太大了"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("①onErrorReturn(Func1)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("①onErrorReturn(Func1)->onError:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("①onErrorReturn(Func1)->onNext:" + integer);
            }
        });

        print("\n-------------------------Exception");

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //会忽略onError调用，不会将错误传递给观察者
                        subscriber.onError(new Exception("i太大了"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("①onErrorReturn(Func1)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("①onErrorReturn(Func1)->onError:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("①onErrorReturn(Func1)->onNext:" + integer);
            }
        });


        print("\n-------------------------onErrorReturn");

        /*
 * ①.onErrorReturn：
 * 返回一个原有Observable行为的新Observable镜像，
 * 后者会忽略前者的onError调用，不会将错误传递给观察者，
 * 作为替代，它会发发射一个特殊的项并调用观察者的onCompleted方法
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //会忽略onError调用，不会将错误传递给观察者
                        subscriber.onError(new Throwable("i太大了"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).onErrorReturn(new Func1<Throwable, Integer>() {
            //作为替代，它会发发射一个特殊的项并调用观察者的onCompleted方法。
            @Override
            public Integer call(Throwable throwable) {
                return 10;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("①onErrorReturn(Func1)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("①onErrorReturn(Func1)->onError:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("①onErrorReturn(Func1)->onNext:" + integer);
            }
        });


        print("\n-------------------------onErrorResumeNext");


/*
 * ②.onErrorResumeNext(Observable):
 * 当原Observable发射onError消息时，会忽略onError消息，不会传递给观察者；
 * 然后它会开始另一个备用的Observable，继续发射数据
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //会忽略onError调用，不会将错误传递给观察者
                        subscriber.onError(new Throwable("i太大了"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).onErrorResumeNext(Observable.create(new Observable.OnSubscribe<Integer>() {

            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 10; i < 13; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        })).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("②onErrorResumeNext(Observable)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("②onErrorResumeNext(Observable)->onError:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("②onErrorResumeNext(Observable)->onNext:" + integer);
            }
        });


        print("\n-------------------------onErrorResumeNext");
/*
 * ③.onErrorResumeNext(Func1):
 * 和onErrorResumeNext(Observable)相似，但他能截取到原Observable的onError消息
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //会忽略onError调用，不会将错误传递给观察者
                        subscriber.onError(new Throwable("i太大了"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).onErrorResumeNext(new Func1<Throwable, Observable<? extends Integer>>() {
            @Override
            public Observable<? extends Integer> call(Throwable throwable) {
                //throwable就是原Observable发射的onError消息中的Throwable对象
                print("③onErrorResumeNext(Func1)->throwable:" + throwable.getMessage());
                //如果原Observable发射了onError消息，将会开启下面的Observable
                return Observable.create(new Observable.OnSubscribe<Integer>() {
                    @Override
                    public void call(Subscriber<? super Integer> subscriber) {
                        for (int i = 100; i < 103; i++) {
                            subscriber.onNext(i);
                        }
                        subscriber.onCompleted();
                    }
                });
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("③onErrorResumeNext(Func1)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("③onErrorResumeNext(Func1)->onError:" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("onErrorResumeNext(Func1)->onNext:" + integer);
            }
        });


        print("\n-------------------------onExceptionResumeNext");

/*
 * ④.onExceptionResumeNext：
 *    和onErrorResumeNext类似，可以说是onErrorResumeNext的特例，
 *    区别是如果onError收到的Throwable不是一个Exception，它会将错误传递给观察者的onError方法，不会使用备用的Observable。
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 10; i++) {
                    if (i > 3) {
                        //如果不是Exception，错误会传递给观察者，不会开启备用Observable
                        //subscriber.onError(new Throwable("i太大了"));
                        //如果Exception，不会将错误传递给观察者，并会开启备用Observable
                        subscriber.onError(new Exception("i太大了哦哦哦"));
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).onExceptionResumeNext(Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 10; i < 13; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        })).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("④onExceptionResumeNext(Observable)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("④onExceptionResumeNext(Observable)->onError:" + e.getClass().getSimpleName() + ":" + e.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                print("④onExceptionResumeNext(Observable)->onNext:" + integer);
            }
        });


    }

    public void testRetry() {
        /**
         * ①. retry()
         *     无限次尝试重新订阅
         */
//        Observable.create(new Observable.OnSubscribe<Integer>() {
//            @Override
//            public void call(Subscriber<? super Integer> subscriber) {
//                for (int i = 0; i < 3; i++) {
//                    if (i == 1) {
//                        print("①retry()->onError");
//                        subscriber.onError(new RuntimeException("always fails"));
//                    } else {
//                        subscriber.onNext(i);
//                    }
//                }
//            }
//        }).retry()    //无限次尝试重新订阅
//                .subscribe(new Subscriber<Integer>() {
//                    @Override
//                    public void onCompleted() {
//                        print("①retry()->onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        print("①retry()->onError" + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(Integer i) {
//                        print("①retry()->onNext" + i);
//                    }
//                });

/**
 * ②. retry(count)
 *     最多2次尝试重新订阅
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 3; i++) {
                    if (i == 1) {
                        print("②retry(count)->onError");
                        subscriber.onError(new RuntimeException("always fails"));
                    } else {
                        subscriber.onNext(i);
                    }
                }
            }
        }).retry(2)    //最多尝试2次重新订阅
                .subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        print("②retry(count)->onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        print("②retry(count)->onError" + e.getMessage());
                    }

                    @Override
                    public void onNext(Integer i) {
                        print("②retry(count)->onNext" + i);
                    }
                });

/**
 * ③. retry(Func2)
 */
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 3; i++) {
                    if (i == 1) {
                        print("③retry(Func2)->onError");
                        subscriber.onError(new RuntimeException("always fails"));
                    } else {
                        subscriber.onNext(i);
                    }
                }
            }
        }).retry(new Func2<Integer, Throwable, Boolean>() {
            @Override
            public Boolean call(Integer integer, Throwable throwable) {
                print("③发生错误了：" + throwable.getMessage() + ",第" + integer + "次重新订阅");
                if (integer > 2) {
                    return false;//不再重新订阅
                }
                //此处也可以通过判断throwable来控制不同的错误不同处理
                return true;
            }
        }).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                print("③retry(Func2)->onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("③retry(Func2)->onError" + e.getMessage());
            }

            @Override
            public void onNext(Integer i) {
                print("③retry(Func2)->onNext" + i);
            }
        });
    }

    private int count = 0;

    public void testRetryWhen() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                print("+++++++++sub");
                subscriber.onError(new Throwable("test error"));
            }
        }).retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
            @Override
            public Observable<?> call(Observable<? extends Throwable> observable) {
                return observable.flatMap(new Func1<Throwable, Observable<?>>() {
                    @Override
                    public Observable<?> call(Throwable throwable) {
                        print("+++++++++cou:" + count);
                        if (count++ < 3) {
                            return Observable.timer(1, TimeUnit.SECONDS);
                        }
                        return Observable.error(throwable);
                    }
                });
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                print("+++++++++onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                print("+++++++++onError:" + e.toString());
            }

            @Override
            public void onNext(String s) {
                print("+++++++++onNext:" + s);
            }
        });
    }


    private void print(String str) {
        System.out.println(str);
    }

}
