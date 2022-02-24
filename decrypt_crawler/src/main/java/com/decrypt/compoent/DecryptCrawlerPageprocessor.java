package com.decrypt.compoent;

import cn.hutool.script.JavaScriptEngine;
import cn.hutool.script.ScriptUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import javax.script.ScriptException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2022/02/23 21:10
 * @description: 解析页面
 */
public class DecryptCrawlerPageprocessor implements PageProcessor {
    @Override
    public void process(Page page) {
        System.out.println(1111);
    }

    @Override
    public Site getSite() {
        return Site.me();
    }


    public static void main(String[] args) throws Exception{

        String uname = "ucap6500000090";
        String pwd = String.valueOf(new Date().getTime());

        String username = excuteJs(uname);
        String password = excuteJs(pwd);

        String url = "http://www.xinjiang.gov.cn/guestweb/sHtml";
        Request request = new Request(url);
        request.setMethod(HttpConstant.Method.POST);
        Map<String,Object> param = new HashMap<>();
        param.put("searchWord", URLEncoder.encode("医保"));
        param.put("siteCode","6500000090");
        param.put("column",URLEncoder.encode("全部"));
        param.put("wordPlace","1");
        param.put("orderBy","0");
        param.put("startTime","");
        param.put("endTime","");
        param.put("pageSize","10");
        param.put("pageNum","0");
        param.put("timeStamp","0");
        param.put("sonSiteCode","");
        param.put("checkHandle","1");
        param.put("strFileType","全部格式");
        param.put("areaSearchFlag","");
        param.put("secondSearchWords","");
        param.put("countKey","0");
        param.put("uc","0");
        param.put("userName",username);
        param.put("passWord",password);


        request.setRequestBody(HttpRequestBody.form(param,"utf-8"));

        Spider.create(new DecryptCrawlerPageprocessor())
                .addRequest(request)
                .thread(1)
                .start();
    }

    public static String excuteJs(String str) throws ScriptException, NoSuchMethodException {

        String js = "function SM4_Context(){this.mode=1;this.isPadding=true;this.sk=new Array(32)}function SM4(){this.SM4_ENCRYPT=1;this.SM4_DECRYPT=0;var SboxTable=[214,144,233,254,204,225,61,183,22,182,20,194,40,251,44,5,43,103,154,118,42,190,4,195,170,68,19,38,73,134,6,153,156,66,80,244,145,239,152,122,51,84,11,67,237,207,172,98,228,179,28,169,201,8,232,149,128,223,148,250,117,143,63,166,71,7,167,252,243,115,23,186,131,89,60,25,230,133,79,168,104,107,129,178,113,100,218,139,248,235,15,75,112,86,157,53,30,36,14,94,99,88,209,162,37,34,124,59,1,33,120,135,212,0,70,87,159,211,39,82,76,54,2,231,160,196,200,158,234,191,138,210,64,199,56,181,163,247,242,206,249,97,21,161,224,174,93,164,155,52,26,85,173,147,50,48,245,140,177,227,29,246,226,46,130,102,202,96,192,41,35,171,13,83,78,111,213,219,55,69,222,253,142,47,3,255,106,114,109,108,91,81,141,27,175,146,187,221,188,127,17,217,92,65,31,16,90,216,10,193,49,136,165,205,123,189,45,116,208,18,184,229,180,176,137,105,151,74,12,150,119,126,101,185,241,9,197,110,198,132,24,240,125,236,58,220,77,32,121,238,95,62,215,203,57,72];var FK=[2746333894,1453994832,1736282519,2993693404];var CK=[462357,472066609,943670861,1415275113,1886879365,2358483617,2830087869,3301692121,3773296373,4228057617,404694573,876298825,1347903077,1819507329,2291111581,2762715833,3234320085,3705924337,4177462797,337322537,808926789,1280531041,1752135293,2223739545,2695343797,3166948049,3638552301,4110090761,269950501,741554753,1213159005,1684763257];this.GET_ULONG_BE=function(b,i){return(b[i]&255)<<24|((b[i+1]&255)<<16)|((b[i+2]&255)<<8)|(b[i+3]&255)&4294967295};this.PUT_ULONG_BE=function(n,b,i){var t1=(255&(n>>24));var t2=(255&(n>>16));var t3=(255&(n>>8));var t4=(255&(n));b[i]=t1>128?t1-256:t1;b[i+1]=t2>128?t2-256:t2;b[i+2]=t3>128?t3-256:t3;b[i+3]=t4>128?t4-256:t4};this.SHL=function(x,n){return(x&4294967295)<<n};this.ROTL=function(x,n){var s=this.SHL(x,n);var ss=x>>(32-n);return this.SHL(x,n)|x>>(32-n)};this.sm4Lt=function(ka){var bb=0;var c=0;var a=new Array(4);var b=new Array(4);this.PUT_ULONG_BE(ka,a,0);b[0]=this.sm4Sbox(a[0]);b[1]=this.sm4Sbox(a[1]);b[2]=this.sm4Sbox(a[2]);b[3]=this.sm4Sbox(a[3]);bb=this.GET_ULONG_BE(b,0);c=bb^this.ROTL(bb,2)^this.ROTL(bb,10)^this.ROTL(bb,18)^this.ROTL(bb,24);return c};this.sm4F=function(x0,x1,x2,x3,rk){return x0^this.sm4Lt(x1^x2^x3^rk)};this.sm4CalciRK=function(ka){var bb=0;var rk=0;var a=new Array(4);var b=new Array(4);this.PUT_ULONG_BE(ka,a,0);b[0]=this.sm4Sbox(a[0]);b[1]=this.sm4Sbox(a[1]);b[2]=this.sm4Sbox(a[2]);b[3]=this.sm4Sbox(a[3]);bb=this.GET_ULONG_BE(b,0);rk=bb^this.ROTL(bb,13)^this.ROTL(bb,23);return rk};this.sm4Sbox=function(inch){var i=inch&255;var retVal=SboxTable[i];return retVal>128?retVal-256:retVal};this.sm4_setkey_enc=function(ctx,key){if(ctx==null){alert(\"ctx is null!\");return false}if(key==null||key.length!=32){alert(\"key error!\");return false}ctx.mode=this.SM4_ENCRYPT;this.sm4_setkey(ctx.sk,key)};this.sm4_setkey=function(SK,key){var MK=new Array(4);var k=new Array(36);var i=0;MK[0]=this.GET_ULONG_BE(key,0);MK[1]=this.GET_ULONG_BE(key,4);MK[2]=this.GET_ULONG_BE(key,8);MK[3]=this.GET_ULONG_BE(key,12);k[0]=MK[0]^FK[0];k[1]=MK[1]^FK[1];k[2]=MK[2]^FK[2];k[3]=MK[3]^FK[3];for(var i=0;i<32;i++){k[(i+4)]=(k[i]^this.sm4CalciRK(k[(i+1)]^k[(i+2)]^k[(i+3)]^CK[i]));SK[i]=k[(i+4)]}};this.padding=function(input,mode){if(input==null){return null}var ret=null;if(mode==this.SM4_ENCRYPT){var p=parseInt(16-input.length%16);ret=input.slice(0);for(var i=0;i<p;i++){ret[input.length+i]=p}}else{var p=input[input.length-1];ret=input.slice(0,input.length-p)}return ret};this.sm4_one_round=function(sk,input,output){var i=0;var ulbuf=new Array(36);ulbuf[0]=this.GET_ULONG_BE(input,0);ulbuf[1]=this.GET_ULONG_BE(input,4);ulbuf[2]=this.GET_ULONG_BE(input,8);ulbuf[3]=this.GET_ULONG_BE(input,12);while(i<32){ulbuf[(i+4)]=this.sm4F(ulbuf[i],ulbuf[(i+1)],ulbuf[(i+2)],ulbuf[(i+3)],sk[i]);i++}this.PUT_ULONG_BE(ulbuf[35],output,0);this.PUT_ULONG_BE(ulbuf[34],output,4);this.PUT_ULONG_BE(ulbuf[33],output,8);this.PUT_ULONG_BE(ulbuf[32],output,12)};this.sm4_crypt_ecb=function(ctx,input){if(input==null){alert(\"input is null!\")}if((ctx.isPadding)&&(ctx.mode==this.SM4_ENCRYPT)){input=this.padding(input,this.SM4_ENCRYPT)}var i=0;var length=input.length;var bous=new Array();for(;length>0;length-=16){var out=new Array(16);var ins=input.slice(i*16,(16*(i+1)));this.sm4_one_round(ctx.sk,ins,out);bous=bous.concat(out);i++}var output=bous;if(ctx.isPadding&&ctx.mode==this.SM4_DECRYPT){output=this.padding(output,this.SM4_DECRYPT)}for(var i=0;i<output.length;i++){if(output[i]<0){output[i]=output[i]+256}}return output};this.sm4_crypt_cbc=function(ctx,iv,input){if(iv==null||iv.length!=16){alert(\"iv error!\")}if(input==null){alert(\"input is null!\")}if(ctx.isPadding&&ctx.mode==this.SM4_ENCRYPT){input=this.padding(input,this.SM4_ENCRYPT)}var i=0;var length=input.length;var bous=new Array();if(ctx.mode==this.SM4_ENCRYPT){var k=0;for(;length>0;length-=16){var out=new Array(16);var out1=new Array(16);var ins=input.slice(k*16,(16*(k+1)));\n" +
                "for(i=0;i<16;i++){out[i]=(ins[i]^iv[i])}this.sm4_one_round(ctx.sk,out,out1);iv=out1.slice(0,16);bous=bous.concat(out1);k++}}else{var temp=[];var k=0;for(;length>0;length-=16){var out=new Array(16);var out1=new Array(16);var ins=input.slice(k*16,(16*(k+1)));temp=ins.slice(0,16);sm4_one_round(ctx.sk,ins,out);for(i=0;i<16;i++){out1[i]=(out[i]^iv[i])}iv=temp.slice(0,16);bous=bous.concat(out1);k++}}var output=bous;if(ctx.isPadding&&ctx.mode==this.SM4_DECRYPT){output=this.padding(output,this.SM4_DECRYPT)}for(var i=0;i<output.length;i++){if(output[i]<0){output[i]=output[i]+256}}return output}}function SM4Util(){this.secretKey=\"c0497fb38d6945788eca039124fa61c2\";this.iv=\"\";this.hexString=false;this.encryptData_ECB=function(plainText){try{var sm4=new SM4();var ctx=new SM4_Context();ctx.isPadding=true;ctx.mode=sm4.SM4_ENCRYPT;var keyBytes=stringToByte(this.secretKey);sm4.sm4_setkey_enc(ctx,keyBytes);var encrypted=sm4.sm4_crypt_ecb(ctx,stringToByte(plainText));var cipherText=fromByteArray(encrypted);if(cipherText!=null&&cipherText.trim().length>0){cipherText.replace(/(\\s*|\\t|\\r|\\n)/g,\"\")}return cipherText}catch(e){console.error(e);return null}};this.encryptData_CBC=function(plainText){try{var sm4=new SM4();var ctx=new SM4_Context();ctx.isPadding=true;ctx.mode=sm4.SM4_ENCRYPT;var keyBytes=stringToByte(this.secretKey);var ivBytes=stringToByte(this.iv);sm4.sm4_setkey_enc(ctx,keyBytes);var encrypted=sm4.sm4_crypt_cbc(ctx,ivBytes,stringToByte(plainText));var cipherText=fromByteArray(encrypted);if(cipherText!=null&&cipherText.trim().length>0){cipherText.replace(/(\\s*|\\t|\\r|\\n)/g,\"\")}return cipherText}catch(e){console.error(e);return null}};stringToByte=function(str){var bytes=new Array();var len,c;len=str.length;for(var i=0;i<len;i++){c=str.charCodeAt(i);if(c>=65536&&c<=1114111){bytes.push(((c>>18)&7)|240);bytes.push(((c>>12)&63)|128);bytes.push(((c>>6)&63)|128);bytes.push((c&63)|128)}else{if(c>=2048&&c<=65535){bytes.push(((c>>12)&15)|224);bytes.push(((c>>6)&63)|128);bytes.push((c&63)|128)}else{if(c>=128&&c<=2047){bytes.push(((c>>6)&31)|192);bytes.push((c&63)|128)}else{bytes.push(c&255)}}}}return bytes};byteToString=function(arr){if(typeof arr===\"string\"){return arr}var str=\"\",_arr=arr;for(var i=0;i<_arr.length;i++){var one=_arr[i].toString(2),v=one.match(/^1+?(?=0)/);if(v&&one.length==8){var bytesLength=v[0].length;var store=_arr[i].toString(2).slice(7-bytesLength);for(var st=1;st<bytesLength;st++){store+=_arr[st+i].toString(2).slice(2)}str+=String.fromCharCode(parseInt(store,2));i+=bytesLength-1}else{str+=String.fromCharCode(_arr[i])}}return str}}var n=[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"G\",\"H\",\"I\",\"J\",\"K\",\"L\",\"M\",\"N\",\"O\",\"P\",\"Q\",\"R\",\"S\",\"T\",\"U\",\"V\",\"W\",\"X\",\"Y\",\"Z\",\"a\",\"b\",\"c\",\"d\",\"e\",\"f\",\"g\",\"h\",\"i\",\"j\",\"k\",\"l\",\"m\",\"n\",\"o\",\"p\",\"q\",\"r\",\"s\",\"t\",\"u\",\"v\",\"w\",\"x\",\"y\",\"z\",\"0\",\"1\",\"2\",\"3\",\"4\",\"5\",\"6\",\"7\",\"8\",\"9\",\"+\",\"/\"];function l(r){return n[r>>18&63]+n[r>>12&63]+n[r>>6&63]+n[r&63]}function h(r,e,t){var n;var o=[];for(var f=e;f<t;f+=3){n=(r[f]<<16)+(r[f+1]<<8)+r[f+2];o.push(l(n))}return o.join(\"\")}function fromByteArray(r){var e;var t=r.length;var o=t%3;var f=\"\";var i=[];var a=16383;for(var u=0,d=t-o;u<d;u+=a){i.push(h(r,u,u+a>d?d:u+a))}if(o===1){e=r[t-1];f+=n[e>>2];f+=n[e<<4&63];f+=\"==\"}else{if(o===2){e=(r[t-2]<<8)+r[t-1];f+=n[e>>10];f+=n[e>>4&63];f+=n[e<<2&63];f+=\"=\"}}i.push(f);return i.join(\"\")}function encodepwdSM4(username){var type=typeof(username);if(type==\"number\"){username=username+\"\"}var sm4=new SM4Util();var encryptData=sm4.encryptData_ECB(username);return encryptData};";


        JavaScriptEngine engine = ScriptUtil.getJavaScriptEngine();
        engine.eval(js);

        String realStr = (String) engine.invokeFunction("encodepwdSM4", str);
        return realStr;
    }
}
