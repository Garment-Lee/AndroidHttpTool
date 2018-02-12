package com.lgf.androidhttptool.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lgf.androidhttptool.log.FLog;
import com.lgf.androidhttptool.network.bean.ConfigFormBean;
import com.lgf.androidhttptool.util.GlobalConstant;
import com.lgf.androidhttptool.util.Md5Utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ligf on 2018/2/7.
 */

public class RetrofitUtil {

    private static RetrofitUtil instance;

    private Retrofit retrofit;

    private AccountService accountService;

    private String BASE_URL = "http://st.careland.com.cn/tc/";

    public static final int APIVER = 1;
    public static final int UMSAVER = 2;
    public static final int RSCHARSET = 1;
    public static final int RSFORMAT = 1;
    public static final int ENCRYPT = 1;
    public static final String PROVER = "1.0";

    private final String debugConfigKey = "D20B600B4C2060EBC21A97AB5557912A"; // 取配置
    private final String releaseConfigKey = "B9720F0D8E5CBCAFC5B6CF409E01C1ED"; // 取配置

    private RetrofitUtil() {

    }

    public static RetrofitUtil getInstance() {
        if (instance == null) {
            synchronized (RetrofitUtil.class) {
                if (instance == null) {
                    instance = new RetrofitUtil();
                }
            }
        }
        return instance;
    }

    public void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(myClient())
                .addConverterFactory(GsonConverterFactory.create(myGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        accountService = retrofit.create(AccountService.class);
    }

    private OkHttpClient myClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
//        client.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                return null;
//            }
//        });
        return client;
    }

    private Gson myGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson;
    }

    public void readConfigFromServer(Observer observer) {
        String sign = "";
        String formatUrl = "";
        String classtypes = "1001001100" + "," + 0;

        if (GlobalConstant.isDebug()) {
            sign = Md5Utils
                    .MD5("classtypes=" + classtypes + "&rscharset=" + RSCHARSET + "&rsformat=" + RSFORMAT
                            + debugConfigKey);

            formatUrl = "http://sttest.careland.com.cn/tc/control_download.php"
                    + "?classtypes=" + classtypes + "&rscharset=" + RSCHARSET + "&rsformat=" + RSFORMAT + "&sign="
                    + sign;
        } else {
            sign = Md5Utils
                    .MD5("classtypes=1001001100,0&rscharset=" + RSCHARSET + "&rsformat=" + RSFORMAT
                            + releaseConfigKey);
            formatUrl = "http://st.careland.com.cn/tc/control_download.php"
                    + "?classtypes=" + classtypes + "&rscharset=" + RSCHARSET + "&rsformat=" + RSFORMAT +
                    "&sign="
                    + sign;
        }
        FLog.i("AndroidHttpTool", "RetrofitUtil readConfigFromServer formatUrl:" + formatUrl);
        Map<String, String> map = new HashMap<>();
        map.put("classtypes", classtypes);
        map.put("rscharset", RSCHARSET + "");
        map.put("rsformat", RSFORMAT + "");
        map.put("sign", sign);


//        accountService.readConfigFromServer(map)
//        accountService.readConfigFromServerV2(formatUrl)
        accountService.readConfigFromServerV3("http://st.careland.com.cn/tc/control_download.php", map)
                .subscribeOn(Schedulers.io())    //subscribeOn:指定执行的线程
                .map(new Function<ConfigFormBean, String>() {
                    @Override
                    public String apply(ConfigFormBean configFormBean) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


//                .flatMap(new Function<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> apply(String s) throws Exception {
//                        FLog.i("AndroidHttpTool", "RetrofitUtil readConfigFromServer s:" + s);
//                        return Observable.just(s);
//                    }
//                }).subscribe(observer);
    }

}
