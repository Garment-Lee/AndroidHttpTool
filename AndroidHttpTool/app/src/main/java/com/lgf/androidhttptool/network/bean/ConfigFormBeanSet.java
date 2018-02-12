package com.lgf.androidhttptool.network.bean;

import java.io.Serializable;

/**
 * Created by ligf on 2018/2/7.
 */

public class ConfigFormBeanSet implements Serializable {

    public static class Item implements Serializable{
        public String classtype;
        public String classname;
        public String classdesc;
        public ConfigItem configitem;
        public String version;
    }

    public static class ConfigItem implements Serializable{
        public String svr_kz;
        public String svr_kz_v1;
        public String sms_num_cmcc;
        public String sms_num_cucc;
        public String phone_hdsc;

        public String reg_express;

        public String svr_bd;

        public String svr_kaccount;

        public String svr_msg;

        public String svr_onlinenavi;

        public String svr_website;

        public String svr_ppt;

        public String svr_pos;

        public String svr_authcheck;

        public String svr_kc;

        public String svr_rti;

        public String svr_lk;

        public String svr_tmc;
        public String svr_cmpub;
        public String svr_ksim;

        public String svr_kzu;
        public String svr_kstat;
        public String svr_ksearch;
        public String svr_kweather;
        public String svr_klogo;
        public String svr_kfeedback;
        public String svr_khygroup;
        public String svr_ksubway;
        public String svr_kteam;
        public String svr_update;
        public String svr_kloc;
        public String svr_kgo;
        public String svr_genuine;
        public String svr_weixin;
        public String svr_download;
        public String svr_hy_download;
        public String svc_qcode;
        public String svc_kpaypoi;
        public String svr_edata;
        public String svr_onlinenavi1;
        public String svr_kfriend;

        public String qq_num;
        public String ronglian_connect_svr;
        public String ronglian_lvs_svr;
        public String ronglian_fileserver_svr;









    }
}
