package cn.fcw.tran.common.utils;

/**
 * Redis所有Keys
 *
 * @author admin
 */
public class RedisKeys {

    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }

    public static String getShiroSessionKey(String key) {
        return "sessionid:" + key;
    }
}
