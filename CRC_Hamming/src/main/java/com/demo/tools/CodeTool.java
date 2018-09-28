package com.demo.tools;

import java.math.BigInteger;

/**
 * @program: CRCTool
 * @description: 二进制与字符串相关的转化
 * @author: xw
 * @create: 2018-09-28 14:08
 */
public class CodeTool {
    /**
     * @Description: 二进制生字符串生成BigInteger
     * @Param:
     * @Return:
     * @Author: xw
     * @Date: 18-9-28
     */
    public BigInteger byteToBigInter(String str){

        char [] nums = new StringBuffer(str).reverse().toString().toCharArray();

        BigInteger sum = new BigInteger("0");
        BigInteger b1 = new BigInteger(String.valueOf(nums[0]));
        BigInteger b2 = new BigInteger("2");
        BigInteger b3 = new BigInteger("1");
        sum = sum.add(b1);
        for(int i=1;i<nums.length;i++){
            b1 = new BigInteger(String.valueOf(nums[i]));
            sum = sum.add(b1.multiply(b2.multiply(b3)));
            b3 = b3.multiply(b2);
        }
        return sum;
    }


    /**
     * @Description: 字符串转为二进制字符串
     * @Param:
     * @Return:
     * @Author: xw
     * @Date: 18-9-28
     */
    public String strToByte(String str){
        char[] strChar=str.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<strChar.length;i++){
            stringBuffer.append(Integer.toBinaryString(strChar[i]));
        }
        return stringBuffer.toString();
    }
}
