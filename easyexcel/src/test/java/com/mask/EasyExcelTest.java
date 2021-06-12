package com.mask;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.mask.easyexcel.domain.Student;
import com.mask.easyexcel.listener.StudentListener;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:32
 * @description: 测试类
 */
public class EasyExcelTest {


    @Test
    public void test01() {
        // 1. 创建工作簿对象
        ExcelReaderBuilder readWorkBook = EasyExcel.read("杭州黑马在线202003班学员信息表.xlsx", Student.class, new StudentListener());
        // 2.获取工作表
        ExcelReaderSheetBuilder sheet = readWorkBook.sheet();
        // 3.读数据
        sheet.doRead();
    }


    @Test
    public void test02() {
        //1. 创建工作部对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write("杭州黑马在线202003班学员信息表-write.xlsx", Student.class);
        // 2. 获取工作表
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        // 3. 写数据
        sheet.doWrite(getStudent());


    }

    private static List getStudent() {
        List<Student> list = new ArrayList<Student>();
        for (int i = 0; i < 10; i++) {
            Student student = new Student();
            student.setName("杭州0" + i);
            student.setBirthday(new Date());
            student.setGender("男");
            if (i%2==0){
                student.setGender("女");
            }
            list.add(student);
        }
        return list;
    }
}
