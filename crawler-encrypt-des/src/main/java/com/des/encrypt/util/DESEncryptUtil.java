package com.des.encrypt.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.script.JavaScriptEngine;

import javax.script.ScriptException;

/**
 * @author: Mask.m
 * @create: 2022/04/05 12:58
 * @description: des加密
 */
public class DESEncryptUtil {

    /**
     * 加密
     *
     * @param content
     * @return
     */
    public static String encrypt(String content) throws ScriptException, NoSuchMethodException {

        //随机生成密钥
        byte[] key = "longrise211thsssdsaqqas".getBytes();

        //构建
        DES des = SecureUtil.des(key);

        //加密为base64字符串
        String encryptHex = des.encryptBase64(content);
        System.out.println("encryptHex = " + encryptHex);


        String script = "function f(st){var pasArr=['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','0','1','2','3','4','5','6','7','8','9','_','-','$','%','&','@','+','!'];for(var i=st.length;i>0;i--){var randomNum=0+Math.floor(Math.random()*(68-0+1));st=st.slice(0,i-1)+pasArr[randomNum]+st.slice(i-1)}return st}";

        JavaScriptEngine engine = JavaScriptEngine.instance();

        engine.eval(script);

        String result = engine.invokeFunction("f", encryptHex).toString();


        return result;
    }


}
