package com.mask.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mask.mapper.ResourceMapper;
import com.mask.pojo.Resource;
import com.mask.service.ResourceService;
import org.springframework.stereotype.Service;

/**
 * @author Mask.m
 * @version 1.0
 * @date 2021/10/26 14:47
 * @Description:
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {
}
