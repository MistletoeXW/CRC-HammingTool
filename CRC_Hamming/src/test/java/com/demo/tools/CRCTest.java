package com.demo.tools;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest
public class CRCTest {


    @Test
    public void getCRC(){
        CodeTool codeTool = new CodeTool();
        CRC crc = new CRC();
        String data = codeTool.strToByte("1aasaasasz");
        System.out.print(crc.getCRC(data,"11001"));

    }

    @Test
    public void Text() {
        CRC crc = new CRC();
        System.out.print(crc.CRCCheck("1aasaasasz","11001","0111"));
        //System.out.print(crc.strToByte("bs"));
        //System.out.print(crc.byteToBigInter("11110"));
    }
}