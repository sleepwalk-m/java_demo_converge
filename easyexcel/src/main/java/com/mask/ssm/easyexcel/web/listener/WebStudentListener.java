package com.mask.ssm.easyexcel.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mask.ssm.easyexcel.domain.Student;
import com.mask.ssm.easyexcel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:53
 * @description:
 */
@Component
@Scope("prototype") // 要求是要多例的监听器
public class WebStudentListener extends AnalysisEventListener<Student> {

    @Autowired
    private StudentService studentService;

    // 每读5条数据 保存一次
    public static final Integer SAVE_DATA_NUM = 5;

    List<Student> list = new ArrayList<Student>();

    /**
     * 每读一行，会调用该方法一次
     *
     * @param student
     * @param analysisContext
     */
    public void invoke(Student student, AnalysisContext analysisContext) {
        list.add(student);
        if (list.size() % 5 == 0) {
            studentService.readExcel(list);
            list.clear();
        }
    }

    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
