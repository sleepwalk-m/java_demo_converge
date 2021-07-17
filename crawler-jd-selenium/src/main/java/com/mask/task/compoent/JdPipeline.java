package com.mask.task.compoent;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mask.task.mapper.ItemMapper;
import com.mask.task.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/07/17 10:33
 * @description: 自定义pipeline保存数据库
 */
@Component
public class JdPipeline implements Pipeline {

    @Autowired
    private ItemMapper itemMapper;

    /**
     * 保存数据库
     *
     * @param resultItems 返回的结果集
     * @param task
     */
    @Override
    public void process(ResultItems resultItems, Task task) {
        List<Item> itemList = resultItems.get("itemList");
        if (itemList != null){
            // 说明是列表页保存的数据
            for (Item item : itemList) {
                itemMapper.insert(item);
            }
        }else {
            // 没有的话就是更新数据
            Item item = resultItems.get("item");
            itemMapper.update(item, Wrappers.<Item>lambdaUpdate().eq(Item::getSku,item.getSku()));
        }
    }
}
