package com.xss.wrapper;

import org.owasp.validator.html.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

public class XssRequestWrapper extends HttpServletRequestWrapper {
    /**
     * 策略文件 需要将要使用的策略文件放到项目资源文件路径下
     * */
    private static String antiSamyPath = XssRequestWrapper.class.getClassLoader()
            .getResource( "antisamy-test.xml").getFile();

    public static  Policy policy = null;
    static {
        // 指定策略文件
        try {
            policy = Policy.getInstance(antiSamyPath);
        } catch (PolicyException e) {
            e.printStackTrace();
        }
    }

    /**
     * AntiSamy过滤数据
     * @param taintedHTML 需要进行过滤的数据
     * @return 返回过滤后的数据
     * */
    private String xssClean( String taintedHTML){
        try{
            // 使用AntiSamy进行过滤
            AntiSamy antiSamy = new AntiSamy();
            CleanResults cr = antiSamy.scan( taintedHTML, policy);
            taintedHTML = cr.getCleanHTML();
        }catch( ScanException e) {
            e.printStackTrace();
        }catch( PolicyException e) {
            e.printStackTrace();
        }
        return taintedHTML;
    }

    public XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    @Override
    public String[] getParameterValues(String name){
        String[] values = super.getParameterValues(name);
        if ( values == null){
            return null;
        }
        int len = values.length;
        String[] newArray = new String[len];
        for (int j = 0; j < len; j++){
            // 过滤清理
            newArray[j] = xssClean(values[j]);
        }
        return newArray;
    }

    @Override
    public String getParameter(String paramString) {
        String str = super.getParameter(paramString);
        if (str == null) {
            return null;
        }
        return xssClean(str);
    }


    @Override
    public String getHeader(String paramString) {
        String str = super.getHeader(paramString);
        if (str == null) {
            return null;
        }
        return xssClean(str);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> requestMap = super.getParameterMap();
        for (Map.Entry<String, String[]> me : requestMap.entrySet()) {
            String[] values = me.getValue();
            for (int i = 0; i < values.length; i++) {
                values[i] = xssClean(values[i]);
            }
        }
        return requestMap;
    }
}