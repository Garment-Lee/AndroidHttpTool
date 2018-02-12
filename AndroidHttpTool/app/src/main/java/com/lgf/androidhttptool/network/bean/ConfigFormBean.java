package com.lgf.androidhttptool.network.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ligf on 2018/2/7.
 */

public class ConfigFormBean implements Serializable {

    public String errcode;
    public String errmsg;
    public ArrayList<ConfigFormBeanSet.Item> item;
    public String server_time;
}
