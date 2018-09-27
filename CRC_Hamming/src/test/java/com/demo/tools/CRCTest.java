package com.demo.tools;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.print.DocFlavor;

import java.math.BigInteger;
import java.util.Scanner;

import static org.junit.Assert.*;


@SpringBootTest
public class CRCTest {

    @Test
    public void getCRC(){
        CRC crc = new CRC();
        System.out.print(crc.getCRC("110011","11001"));
    }

    @Test
    public void Text() {
        CRC crc = new CRC();
        System.out.print(crc.CRCCheck("110011","11001","1001"));
    }
}