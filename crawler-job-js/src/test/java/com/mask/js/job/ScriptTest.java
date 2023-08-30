package com.mask.js.job;

import cn.hutool.script.JavaScriptEngine;
import cn.hutool.script.ScriptUtil;

import javax.script.ScriptException;

/**
 * @author: Mask.m
 * @create: 2023/08/04 22:58
 * @description:
 */

public class ScriptTest {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        String arg1 = "D68247CAD395B353D0E3FD1BC5BECA7C844B82CE";
        String acwCookie = getAcwCookie(arg1);
        System.out.println("acwCookie = " + acwCookie);
    }


    public static String getAcwCookie(String arg1) throws ScriptException, NoSuchMethodException {
        String script = "function getAcwCookie(arg1){var _0x5e8b26=\"3000176000856006061501533003690027800375\";String[\"prototype\"][\"hexXor\"]=function(_0x4e08d8){var _0x5a5d3b=\"\";for(var _0xe89588=0;_0xe89588<this[\"length\"]&&_0xe89588<_0x4e08d8[\"length\"];_0xe89588+=2){var _0x401af1=parseInt(this[\"slice\"](_0xe89588,_0xe89588+2),16);var _0x105f59=parseInt(_0x4e08d8[\"slice\"](_0xe89588,_0xe89588+2),16);var _0x189e2c=(_0x401af1^_0x105f59)[\"toString\"](16);if(_0x189e2c[\"length\"]==1){_0x189e2c=\"0\"+_0x189e2c}_0x5a5d3b+=_0x189e2c}return _0x5a5d3b};String[\"prototype\"][\"unsbox\"]=function(){var _0x4b082b=[15,35,29,24,33,16,1,38,10,9,19,31,40,27,22,23,25,13,6,11,39,18,20,8,14,21,32,26,2,30,7,4,17,5,3,28,34,37,12,36];var _0x4da0dc=[];var _0x12605e=\"\";for(var _0x20a7bf=0;_0x20a7bf<this[\"length\"];_0x20a7bf++){var _0x385ee3=this[_0x20a7bf];for(var _0x217721=0;_0x217721<_0x4b082b[\"length\"];_0x217721++){if(_0x4b082b[_0x217721]==_0x20a7bf+1){_0x4da0dc[_0x217721]=_0x385ee3}}}_0x12605e=_0x4da0dc[\"join\"](\"\");return _0x12605e};var _0x23a392=arg1[\"unsbox\"]();arg2=_0x23a392[\"hexXor\"](_0x5e8b26);return arg2}";


        JavaScriptEngine engine = ScriptUtil.getJavaScriptEngine();
        engine.eval(script);

        String realStr = (String) engine.invokeFunction("getAcwCookie", arg1);
        return realStr;
    }
}
