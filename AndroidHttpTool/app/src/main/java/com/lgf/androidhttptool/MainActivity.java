package com.lgf.androidhttptool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lgf.androidhttptool.log.FLog;
import com.lgf.androidhttptool.network.RetrofitUtil;
import com.lgf.androidhttptool.network.RxJavaUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FLog.setLogcatEnable(true);
        init();
        beginToTest();
        readConfigFromServer();
    }

    private void init(){
        RetrofitUtil.getInstance().initRetrofit();
    }

    private void beginToTest(){
        RxJavaUtil.getInstance().test1();
        RxJavaUtil.getInstance().test2();
        RxJavaUtil.getInstance().test3();
        RxJavaUtil.getInstance().test4();
    }

    private void readConfigFromServer(){
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                FLog.i("AndroidHttpTool", "MainActivity readConfigFromServer result:" + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        RetrofitUtil.getInstance().readConfigFromServer(observer);
    }
}
