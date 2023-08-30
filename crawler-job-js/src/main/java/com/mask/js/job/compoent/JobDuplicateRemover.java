package com.mask.js.job.compoent;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.scheduler.component.DuplicateRemover;

/**
 * @author: Mask.m
 * @create: 2023/08/04 23:44
 * @description:
 */
public class JobDuplicateRemover implements DuplicateRemover {
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
