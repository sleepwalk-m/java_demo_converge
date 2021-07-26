package com.mask.ssm.crawler.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mask.ssm.crawler.mapper.ItemMapper;
import com.mask.ssm.crawler.pojo.Item;
import com.mask.ssm.crawler.service.ItemService;
import org.springframework.stereotype.Service;

/**
 * @author: Mask.m
 * @create: 2021/07/10 14:19
 * @description:
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {


    public void saveData(Item item) {
        save(item);
    }
}
