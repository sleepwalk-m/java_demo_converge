package com.mask.mongo;

import com.mask.mongo.pojos.ApComment;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/08/21 15:33
 * @description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MongoTest {


    @Autowired
    private MongoTemplate mongoTemplate;

    // 新增
    @Test
    public void testInsert(){
        for (int i = 0; i < 10; i++) {


        ApComment apComment = new ApComment();
        apComment.setContent("这是一个测试文本");
        apComment.setLikes(20 + i);
        ApComment insert = mongoTemplate.insert(apComment);
        System.out.println("insert = " + insert);
        }
    }


    // 查询
    @Test
    public void testQuery(){
        // 根据id查询
        ApComment apComment = mongoTemplate.findById("5e9c6ea5cc1042a25c70ff11", ApComment.class);
        System.out.println("apComment = " + apComment);

        // 条件查询
        Query query = Query.query(Criteria.where("likes").lt(25));
        // 倒序
        query.with(Sort.by(Sort.Direction.DESC,"likes"));
        // 分页
        Pageable pagerequest = new PageRequest(1,3);
        query.with(pagerequest);
        List<ApComment> apComments = mongoTemplate.find(query, ApComment.class);
        System.out.println(apComments);

    }

    // 删除
    @Test
    public void testDel(){
        DeleteResult id = mongoTemplate.remove(Query.query(Criteria.where("_id").is("6120ad18191251188c6ce2e4")),ApComment.class);
        System.out.println("id = " + id);

    }


    // 更新
    @Test
    public void testUpdate(){
        // 更新的条件
        Query query = Query.query(Criteria.where("_id").is("6120ad9219125157b401ab7d"));
        // 更新字段 可以继续set
        Update update = new Update().set("content","我就测试怎么了？");
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ApComment.class);
        System.out.println("updateResult = " + updateResult);

    }

}
