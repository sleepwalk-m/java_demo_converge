package com.yuanrenxue.subject13;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

/**
 * @author: Mask.m
 * @create: 2022/04/16 08:47
 * @description: 防止去重
 */
public class DonothingDuplicateRemover implements DuplicateRemover {
    @Override
    public boolean isDuplicate(Request request, Task task) {
        return false;
    }

    @Override
    public void resetDuplicateCheck(Task task) {

    }

    @Override
    public int getTotalRequestsCount(Task task) {
        return 0;
    }
}
