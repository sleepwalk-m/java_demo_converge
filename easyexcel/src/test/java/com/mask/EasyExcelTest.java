package com.mask;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.enums.WriteDirectionEnum;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.mask.easyexcel.domain.FillData;
import com.mask.easyexcel.domain.Student;
import com.mask.easyexcel.listener.StudentListener;
import org.junit.Test;

import java.util.*;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:32
 * @description: 测试类
 */
public class EasyExcelTest {



    /**
     * 练习：报表导出
     */
    @Test
    public void test07() {
        //1.准备模板
        String template = "template/report_template.xlsx";


        // 工作簿
        ExcelWriter workBook = EasyExcel.write("报表导出.xlsx", FillData.class).withTemplate(template).build();

        //工作表
        WriteSheet sheet = EasyExcel.writerSheet().build();

        // 准备数据
        // ****** 准备数据 *******
        // 日期
        HashMap<String, String> dateMap = new HashMap<String, String>();
        dateMap.put("date", "2020-03-16");

        // 总会员数
        HashMap<String, String> totalCountMap = new HashMap<String, String>();
        dateMap.put("totalCount", "1000");

        // 新增员数
        HashMap<String, String> increaseCountMap = new HashMap<String, String>();
        dateMap.put("increaseCount", "100");

        // 本周新增会员数
        HashMap<String, String> increaseCountWeekMap = new HashMap<String, String>();
        dateMap.put("increaseCountWeek", "50");

        // 本月新增会员数
        HashMap<String, String> increaseCountMonthMap = new HashMap<String, String>();
        dateMap.put("increaseCountMonth", "100");

        // 写入统计数据
        workBook.fill(dateMap, sheet);
        workBook.fill(totalCountMap, sheet);
        workBook.fill(increaseCountMap, sheet);
        workBook.fill(increaseCountWeekMap, sheet);
        workBook.fill(increaseCountMonthMap, sheet);
        // 写入新增会员
        workBook.fill(getStudent(), sheet);

        // 切记：这种方式 需要手动关流
        workBook.finish();

    }


    /**
     * 水平方向数据填充 利用模板
     */
    @Test
    public void test06() {
        //1.准备模板
        String template = "template/fill_data_template4.xlsx";


        // 工作簿
        ExcelWriter workBook = EasyExcel.write("Excel填充-水平填充数据.xlsx", FillData.class).withTemplate(template).build();

        //工作表
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // 配置填充参数 选择水平方向
        FillConfig fillConfig = FillConfig.builder().direction(WriteDirectionEnum.HORIZONTAL).build();


        // 准备数据
        List<FillData> fillData = initFillData();

        //多行填充
        workBook.fill(fillData,fillConfig,writeSheet);

        // 切记：这种方式 需要手动关流
        workBook.finish();

    }



    /**
     * 多组 + 单组数据填充 利用模板
     */
    @Test
    public void test05() {
        //1.准备模板
        String template = "template/fill_data_template3.xlsx";


        // 工作簿
        ExcelWriter workBook = EasyExcel.write("Excel填充-多组+单组数据.xlsx", FillData.class).withTemplate(template).build();

        //工作表
        WriteSheet writeSheet = EasyExcel.writerSheet().build();

        // 配置填充参数 多行+单行组合的时候 在多行填充之后必须换行，否则单行数据覆盖多行
        FillConfig fillConfig = FillConfig.builder().forceNewRow(true).build();


        // 准备数据
        List<FillData> fillData = initFillData();

        //多行填充
        workBook.fill(fillData,fillConfig,writeSheet);

        // 单行数据
        Map<String,String> dateAndTotal = new HashMap<String, String>();
        dateAndTotal.put("date","2021-6-1");
        dateAndTotal.put("total","10000");

        workBook.fill(dateAndTotal,writeSheet);

        // 切记：这种方式 需要手动关流
        workBook.finish();



    }


    /**
     * 多组数据填充 利用模板
     */
    @Test
    public void test04() {
        //1.准备模板
        String template = "template/fill_data_template2.xlsx";


        // 工作簿
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充-多组数据.xlsx", FillData.class).withTemplate(template);

        //工作表
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();

        // 准备数据
        List<FillData> fillData = initFillData();

        //填充数据 会在读写结束后关闭流 源码中写到
        sheet.doFill(fillData);

    }


    /**
     * 单组数据填充 利用模板
     */
    @Test
    public void test03() {
        //1.准备模板
        String template = "template/fill_data_template1.xlsx";


        // 工作簿
        ExcelWriterBuilder excelWriterBuilder = EasyExcel.write("Excel填充-单组数据.xlsx", FillData.class).withTemplate(template);

        //工作表
        ExcelWriterSheetBuilder sheet = excelWriterBuilder.sheet();

        // 准备数据
        FillData data = new FillData();
        data.setName("杭州阿里");
        data.setAge(18);

        // 准备map数据
        Map<String,String> data1 = new HashMap<String, String>();
        data1.put("name","杭州阿里MAP");
        data1.put("age","5");

        //填充数据
        sheet.doFill(data1);

    }


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
            if (i % 2 == 0) {
                student.setGender("女");
            }
            list.add(student);
        }
        return list;
    }

    private static List<FillData> initFillData() {
        ArrayList<FillData> fillDatas = new ArrayList<FillData>();
        for (int i = 0; i < 10; i++) {
            FillData fillData = new FillData();
            fillData.setName("杭州黑马0" + i);
            fillData.setAge(10 + i);
            fillDatas.add(fillData);
        }
        return fillDatas;
    }
}
