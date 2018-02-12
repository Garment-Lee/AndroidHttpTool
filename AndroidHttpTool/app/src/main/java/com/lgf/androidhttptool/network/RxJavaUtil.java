package com.lgf.androidhttptool.network;

import com.lgf.androidhttptool.log.FLog;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by ligf on 2018/2/6.
 */

public class RxJavaUtil {

    private static final String TAG = "RxJavaUtil";

    private static RxJavaUtil instance;

    private RxJavaUtil() {

    }

    public static RxJavaUtil getInstance() {
        if (instance == null) {
            synchronized (RxJavaUtil.class) {
                if (instance == null) {
                    instance = new RxJavaUtil();
                }
            }
        }
        return instance;
    }

    Observer<String> observer = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(String value) {
            FLog.i("AndroidHttpTool", "RxJavaUtil onNext value:" + value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    Observable observable = Observable.create(new ObservableOnSubscribe() {
        @Override
        public void subscribe(ObservableEmitter e) throws Exception {
            e.onNext("start to subscribe");
        }
    });

    public void test1() {
        observable.subscribe(observer);
    }

    /**
     * use map to convert one event object
     */
    public void test2() {
        observable.map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return 1;
            }
        })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        FLog.i(TAG, "test2 value1:" + integer);
                        return integer + 1;
                    }
                })
                .map(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        FLog.i(TAG, "test2 value2:" + integer);
                        return integer + 1;
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        FLog.i(TAG, "test2 value3:" + integer);
                        return integer + 1 + "";
                    }
                }).observeOn(Schedulers.io())
        .subscribeOn(AndroidSchedulers.mainThread())
        .subscribe(observer);
    }

    /**
     * use flatmap to convert one event object
     */
    public void test3() {
        observable.flatMap(new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(String s) throws Exception {
                return Observable.just(s + "on test3 covert to test3");
            }
        }).subscribe(observer);
    }

    /**
     * use flatmap to convert a sequence of event object
     */
    public void test4() {
        observable.flatMap(new Function<String, Observable<String>>() {
            @Override
            public Observable<String> apply(String s) throws Exception {
                String[] strings = new String[4];
                strings[0] = "test4 array of one";
                strings[1] = "test4 array of two";
                strings[2] = "test4 array of three";
                strings[3] = "test4 array of four";
                return Observable.fromArray(strings);
            }
        }).subscribe(observer);
    }
}
