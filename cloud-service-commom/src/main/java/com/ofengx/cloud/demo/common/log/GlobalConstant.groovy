package com.ofengx.cloud.demo.common.log;

/**
 *
 * @version
 * @author xiang feng
 *
 */
final class GlobalConstant {

    static final String SQL_TRACE = "SQLTRACE";

    static final String ACCESS_TRACE = "ACCESSTRACE";

    static final String GWS_LOG = "GWS";

    static final String CON_QUOTE = "`";

    static  Boolean SHOW_SQL=false;

    static ThreadLocal<AccessLog> accessLog= new ThreadLocal<AccessLog>();

    static int SID_COOKIE_MAXAGE = 60*60*24*30;

    static String SID_COOKIE_NAME ="sid";

}
