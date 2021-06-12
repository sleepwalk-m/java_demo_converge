package com.mask.easyexcel.service;

import com.mask.easyexcel.domain.Student;

import java.util.List;

/**
 * @author: Mask.m
 * @create: 2021/06/12 14:48
 * @description:
 */
public interface StudentService {

    void readExcel(List<Student> list);
}
