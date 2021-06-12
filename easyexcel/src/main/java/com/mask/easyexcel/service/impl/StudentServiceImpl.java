package com.mask.easyexcel.service.impl;

import com.mask.easyexcel.domain.Student;
import com.mask.easyexcel.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:48
 * @description:
 */
@Service
public class StudentServiceImpl implements StudentService {


    public void readExcel(List<Student> list) {
        for (Student student : list) {
            System.out.println("student = " + student);
        }
    }
}
