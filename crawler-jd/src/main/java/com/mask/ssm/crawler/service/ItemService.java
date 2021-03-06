package com.mask.ssm.crawler.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mask.ssm.crawler.pojo.Item;

/**
 * @author: Mask.m
 * @create: 2021/07/10 14:19
 * @description:
 */
public interface ItemService extends IService<Item> {

    public void saveData(Item item);
}
