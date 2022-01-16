package com.mask.excutejs;

import cn.hutool.script.JavaScriptEngine;
import cn.hutool.script.ScriptUtil;

import javax.script.ScriptException;

/**
 * @author: Mask.m
 * @date: 2022/1/16 14:32
 * @version: v1.0
 * @Description: 星图登录参数的加密执行js
 */
public class XingtuLoginJs {
    public static void main(String[] args) throws ScriptException, NoSuchMethodException {

        String pwd = excuteJs("11111");
        String account = excuteJs("aaaaaaaa@163.com");
        System.out.println("pwd = " + pwd);
        System.out.println("account = " + account);
    }



    public static String excuteJs(String str) throws ScriptException, NoSuchMethodException {

        String js = "function fe(e) {\n" +
                "  var t = []\n" +
                "  var r\n" +
                "  var n = 0\n" +
                "  var a = 0\n" +
                "  for (n = 0; n < e.length; n++) {\n" +
                "    r = e.charCodeAt(n)\n" +
                "    if (0 <= r && r <= 127) t.push(r)\n" +
                "    else if (128 <= r && r <= 2047) {\n" +
                "      t.push(192 | (31 & (r >> 6)))\n" +
                "      t.push(128 | (63 & r))\n" +
                "    } else if ((2048 <= r && r <= 55295) || (57344 <= r && r <= 65535)) {\n" +
                "      t.push(224 | (15 & (r >> 12)))\n" +
                "      t.push(128 | (63 & (r >> 6)))\n" +
                "      t.push(128 | (63 & r))\n" +
                "    }\n" +
                "  }\n" +
                "  for (a = 0; a < t.length; a++) t[a] &= 255\n" +
                "  return t\n" +
                "}\n" +
                "//获取\n" +
                "//pwd=\"www123456\"\n" +
                "//手机号\n" +
                "//account =\"+8613918777711\"\n" +
                "function get_pwd_account(e) {\n" +
                "  var t, r\n" +
                "  var n = []\n" +
                "  var a = []\n" +
                "  if (void 0 === e) return ''\n" +
                "  e = String(e)\n" +
                "  a = fe(e)\n" +
                "  for (t = 0, r = a.length; t < r; ++t) n.push((5 ^ a[t]).toString(16))\n" +
                "  return n.join('')\n" +
                "}";


        JavaScriptEngine engine = ScriptUtil.getJavaScriptEngine();
        engine.eval(js);

        String realStr = (String) engine.invokeFunction("get_pwd_account", str);
        return realStr;
    }
}

