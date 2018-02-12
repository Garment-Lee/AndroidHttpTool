package com.lgf.androidhttptool.util;


/**
 * @param :
 * @author : yuyh
 * @annotation :全局常量
 * @date :2017-10-16 11:03
 * @return :
 **/
public class GlobalConstant {
    private static final boolean isDebug = false;

    private static final int apptype = 72;
    private static final int appid = 31;
    private static final int cid = 1060;
    private static final int bussinessid = 21;

    private static final String requestFailStr = "网络请求失败";
    private static final String noAuthorityStr = "无法获取权限，相关功能无法使用";

    public static boolean isSDKInit = false;

    //app setting key
    public static final String SETTING_DUID_KEY = "duid";
    public static final String SETTING_KUID_KEY = "kuid";
    public static final String SETTING_SESSION_KEY = "session";
    public static final String SETTING_LOGIN_NAME_KEY = "login_name";
    public static final String SETTING_LOGIN_PW_KEY = "login_pw";

    //
    public static final String  totkenKey = "Qw388swWzS8s75Lw98Sr9W87LF91Ww5i";
    public static final String totkenTestKey = "594d063cc2fc1305d36549837a068a7c";

    public static boolean isDebug() {
        return isDebug;
    }

    public static int getApptype() {
        return apptype;
    }

    public static int getAppid() {
        return appid;
    }

    public static int getCid() {
        return cid;
    }

    public static int getBussinessid() {
        return bussinessid;
    }

    public static String getRequestFailStr() {
        return requestFailStr;
    }

    public static String getNoAuthorityStr() {
        return noAuthorityStr;
    }
}
