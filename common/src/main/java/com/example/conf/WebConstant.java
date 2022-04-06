package com.example.conf;

public class WebConstant {
    /**
     * 返回code类型
     * 200  成功  =>
     * 500  失败
     */
    public final static String CODE_OK = "200";
    public final static String CODE_OK_MSG = "操作成功";
    public final static String CODE_ERROR = "500";
    public final static String CODE_ERROR_MSG = "操作失败";


    /**
     * 分页默认参数
     */
    public final static String DEFAULT_PAGE_NO = "1";
    public final static String DEFAULT_PAGE_SIZE = "20";


    /**
     * 请求头中的token
     */
    public final static String HEADER_TOKEN = "token";

    public final static String SESSION_ID = "xxl_sso_sessionid";
}
