package com.example.work;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.text.DateFormatter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
class WorkApplicationTests {

    @Test
    void contextLoads() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        int month = calendar.get(Calendar.MONTH) + 1;
        int year = calendar.get(Calendar.YEAR);
        System.out.println("year = " + year);
        System.out.println("month = " + month);


    }


    public static void main(String[] args) throws ParseException {

        LocalDate now = LocalDate.now();

        LocalDate beforeDate = now.plusDays(-5);
        LocalDate afterDate = now.plusDays(6);

        System.out.println("beforeDate = " + beforeDate);
        System.out.println("afterDate = " + afterDate);


        int year1 = now.getYear();
        System.out.println("year1 = " + year1);

        int monthValue = now.getMonthValue();
        System.out.println("monthValue = " + monthValue);

        int dayOfMonth = now.getDayOfMonth();
        System.out.println("dayOfMonth = " + dayOfMonth);

        String dateTime = "20210525";
        LocalDate date1 = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println("date1 = " + date1);

        if (date1.isBefore(afterDate) && date1.isAfter(beforeDate)){
            System.out.println("成功判断~！！！！");
        }

        //LocalDate firstDay = LocalDate.of(now.getYear(), now.getMonth(), 1);
        LocalDate firstDay = now.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("firstDay = " + firstDay);

        LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("lastDay = " + lastDay);

    }



    @Test
    public void testURLEncode() throws UnsupportedEncodingException {
        String s = "你好";
        String encode = URLEncoder.encode(s, "utf-8");
        System.out.println("encode = " + encode);

    }

}
