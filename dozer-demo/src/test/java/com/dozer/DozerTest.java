package com.dozer;

import com.dozer.dto.UserDTO;
import com.dozer.entity.UserEntity;
import com.github.dozermapper.core.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Mask.m
 * @create: 2021/09/15 21:25
 * @description:
 */
@SpringBootTest
//@RunWith(SpringRunner.class) // org.junit.jupiter.api.Test 用juPiter的包 不必写这个注解
public class DozerTest {

    @Autowired
    Mapper mapper;

    @Test
    public void testDozer1(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");
        userDTO.setBirthday("2010-11-20");
        // 拷贝所有属性
        UserEntity user = mapper.map(userDTO, UserEntity.class);
        System.out.println(user);
    }

    @Test
    public void testDozer2(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");
        userDTO.setBirthday("2010-11-20");

        UserEntity user = new UserEntity();
        user.setId("200");
        System.out.println(user);// id=200
        mapper.map(userDTO,user);
        System.out.println(user);// id=100  如果已经有的对象，作为目标对象拷贝，则相同属性会覆盖
    }

    @Test
    public void testDozer3(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("100");
        userDTO.setUserName("itcast");
        userDTO.setUserAge(20);
        userDTO.setAddress("bj");

        UserEntity user = new UserEntity();
        System.out.println(user);
        mapper.map(userDTO,user,"user");// biz.dozer.xml中指定了mapping标签的map-id，第3个参数可以指定id，否则就是默认第一个
        System.out.println(user);
    }

}
