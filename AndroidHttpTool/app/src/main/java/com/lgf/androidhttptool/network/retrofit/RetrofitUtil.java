package com.lgf.androidhttptool.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.lgf.androidhttptool.log.FLog;
import com.lgf.androidhttptool.network.AccountService;
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
import okhttp3.logging.HttpLoggingInterceptor;
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
                .baseUrl(BASE_URL)  //设置基础url
                .client(myClient())       //设置Http请求客户端
                .addConverterFactory(GsonConverterFactory.create(myGson()))     //设置实体类的序列化和反序列化转换器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())      //设置请求适配器，支持RxJava的Observable，Retrofit Service请求结果封装返回Observable对象
                .build();
        accountService = retrofit.create(AccountService.class);
    }

    private OkHttpClient myClient() {
        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)     //添加日志拦截器，便于调试查看请求Url和请求参数，以及请求结果
                .build();
        return client;
    }

    private Gson myGson() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return gson;
    }

    /**
     * Get请求，从服务器获取配置参数
     * @param observer
     */
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
    }

}
