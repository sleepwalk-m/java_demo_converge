package com.mask.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mask.easyexcel.domain.Student;

/**
 * @Author Vsunks.v
 * @Date 2020/3/16 19:34
 * @Description: 使用EasyExcel读excel文件，需要使用一个监听器，
 * <p>
 * 在读的时候，每读一行，就会自动调用监听器的invoke方法，并且把读取的内容自动封装成了一个对象
 */
public class StudentListener extends AnalysisEventListener<Student> {

    /***
     * 每读一样会自动调用这个方法
     * @param student   读取的内容自动封装成了一个对象
     * @param context
     */
    public void invoke(Student student, AnalysisContext context) {
        System.out.println("student = " + student);

    }

    /**
     * 全部解析完之后，调用
     * @param context
     */
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
