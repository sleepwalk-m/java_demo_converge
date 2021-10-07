package com.jwt.test;

import cn.hutool.core.io.FileUtil;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Mask.m
 * @create: 2021/10/07 10:39
 * @description:
 */
public class JwtTest {

    /**
     * 生成jwt 不使用签名
     */
    @Test
    public void test1(){

        // jwt的header信息
        Map header = new HashMap();
        header.put("alg","none"); //不使用签名算法
        header.put("typ","JWT"); //指定令牌的类型 统一大写JWT

        // 封装jwt的payload信息
        Map body = new HashMap();
        body.put("userId","100"); // 自定义的字段 保存数据
        body.put("account","admin");
        body.put("role","admin");

        // 生成jwt令牌
        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(body)
                .setId("101") // 表示
                .compact();
        System.out.println("token = " + token); // eyJ0eXAiOiJKV1QiLCJhbGciOiJub25lIn0.eyJyb2xlIjoiYWRtaW4iLCJ1c2VySWQiOiIxMDAiLCJhY2NvdW50IjoiYWRtaW4iLCJqdGkiOiIxMDEifQ.


        // 解析jwt令牌
        Jwt jwt = Jwts.parser().parse(token);
        Object body1 = jwt.getBody();
        Header header1 = jwt.getHeader();
        System.out.println("header1 = " + header1);
        System.out.println("body1 = " + body1);
    }

    /**
     * 生成jwt 使用签名算法 hs256
     */
    @Test
    public void test2(){

        // jwt的header信息
        Map header = new HashMap();
        header.put("alg", SignatureAlgorithm.HS256.getValue()); //不使用签名算法
        header.put("typ","JWT"); //指定令牌的类型 统一大写JWT

        // 封装jwt的payload信息
        Map body = new HashMap();
        body.put("userId","100"); // 自定义的字段 保存数据
        body.put("account","admin");
        body.put("role","admin");

        // 生成jwt令牌
        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(body)
                .setId("101") // 表示
                .signWith(SignatureAlgorithm.HS256,"mask")// 指定加密方式以及密钥
                .compact();
        System.out.println("token = " + token);


        // 解析jwt令牌
        Jwt jwt = Jwts.parser().setSigningKey("mask").parse(token);
        Object body1 = jwt.getBody();
        Header header1 = jwt.getHeader();
        System.out.println("header1 = " + header1);
        System.out.println("body1 = " + body1);
    }

    //生成jwt时使用签名算法生成签名部分----基于RS256签名算法
    @Test
    public void test3() throws Exception{
        //添加构成JWT的参数
        Map<String, Object> headMap = new HashMap();
        headMap.put("alg", SignatureAlgorithm.RS256.getValue());//使用RS256签名算法
        headMap.put("typ", "JWT");

        Map body = new HashMap();
        body.put("userId","1");
        body.put("username","xiaoming");
        body.put("role","admin");

        String jwt = Jwts.builder()
                .setHeader(headMap)
                .setClaims(body)
                .setId("jwt001")
                .signWith(SignatureAlgorithm.RS256,getPriKey())
                .compact();
        System.out.println(jwt);

        //解析jwt
        Jwt result = Jwts.parser().setSigningKey(getPubKey()).parse(jwt);
        Object jwtBody = result.getBody();
        Header header = result.getHeader();

        System.out.println(result);
        System.out.println(jwtBody);
        System.out.println(header);
    }

    //获取私钥
    public PrivateKey getPriKey() throws Exception{
        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream("pri.key");
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    //获取公钥
    public PublicKey getPubKey() throws Exception{
        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream("pub.key");
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }


    //生成自己的 秘钥/公钥 对
    @Test
    public void test4() throws Exception{
        //自定义 随机密码,  请修改这里
        String password = "mask";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "d:\\pub.key");
        FileUtil.writeBytes(privateKeyBytes, "d:\\pri.key");
    }

}
