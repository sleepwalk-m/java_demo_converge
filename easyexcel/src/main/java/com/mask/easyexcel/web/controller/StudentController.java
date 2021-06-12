package com.mask.easyexcel.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.mask.easyexcel.domain.Student;
import com.mask.easyexcel.web.listener.WebStudentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:47
 * @description:
 */
@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    WebStudentListener webStudentListener;

    @RequestMapping("read")
    @ResponseBody
    public String readExcel(MultipartFile uploadExcel) {
        try {
            // 工作簿
            ExcelReaderBuilder read = EasyExcel.read(uploadExcel.getInputStream(), Student.class, webStudentListener);
            // 工作表
            ExcelReaderSheetBuilder sheet = read.sheet();
            // 读
            sheet.doRead();
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }


        return "success";
    }


    @RequestMapping("write")
    @ResponseBody
    public void writeExcel(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");

        //1. 创建工作部对象
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(response.getOutputStream(), Student.class);
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
