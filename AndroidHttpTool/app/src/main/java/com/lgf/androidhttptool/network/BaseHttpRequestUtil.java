package com.lgf.androidhttptool.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

/**
 * Created by garment on 2018/1/21.
 */

public class BaseHttpRequestUtil {

    /**
     * execute the http request in GET method of HttpUrlConnection. Return the String as a result.
     * @param url
     * @param paramMap
     * @return
     */
    public static String executeHttpUrlConnectionGet(String url, HashMap<String, String> paramMap){
        String resultString = null;
        URL requestUrl = null;
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        try {
            requestUrl = new URL(url);
            connection = (HttpURLConnection) requestUrl.openConnection();
            inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                stringBuffer.append(line);
            }
            resultString = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null){
                connection.disconnect();
            }
            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultString;
    }
}
