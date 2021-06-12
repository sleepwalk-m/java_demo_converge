package com.mask.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author Vsunks.v
 * @Date 2020/3/16 19:18
 * @Description: lombok
 * 可以通过一个依赖 + 一个插件，就可以在代码编译的时候为实体类自动添加常用的方法
 * @Setter
 * @Getter
 * @ToString
 * @EqualsAndHashCode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ColumnWidth(20) //列宽
@HeadRowHeight(30)  //列头行高
//@ContentRowHeight 内容行高
public class Student {




    //@ColumnWidth(20)
    //@ExcelProperty(value = {"杭州黑马学员信息表", "学生姓名"}/*,index = 1*/)
    @ExcelProperty(value = {"学生姓名"}/*,index = 1*/)
    private String name;



    //@ColumnWidth(20)
    @DateTimeFormat("yyyy-MM-dd")
    //@ExcelProperty(value = {"杭州黑马学员信息表", "学生生日"}/*,index = 3*/)
    @ExcelProperty(value = {"学生出生日期"}/*,index = 3*/)
    private Date birthday;

    //@ExcelProperty(value = {"杭州黑马学员信息表", "学生性别"}/*,index = 2*/)
    @ExcelProperty(value = {"学生性别"}/*,index = 2*/)
    private String gender;

    //@ExcelProperty(value = "学生编号",index = 4)
    @ExcelIgnore
    private String id;
}
