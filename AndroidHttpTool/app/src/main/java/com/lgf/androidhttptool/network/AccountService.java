package com.lgf.androidhttptool.network;

import com.lgf.androidhttptool.network.bean.ConfigFormBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by ligf on 2018/2/7.
 */

public interface AccountService {

    //use the url which is put together with base url and the parameters and the string with the specified characters @GET or @POST
    @GET("control_download.php")
    Observable<ConfigFormBean> readConfigFromServer(@QueryMap Map<String, String> map);

    //use the url which is provided by the parameter with the specified characters @Url
    @GET()
    Observable<ConfigFormBean> readConfigFromServerV2(@Url String url);

    //use the url which is put together with the url and the map provided by the parameter
    @GET
    Observable<ConfigFormBean> readConfigFromServerV3(@Url String url, @QueryMap Map<String, String> map);
}
