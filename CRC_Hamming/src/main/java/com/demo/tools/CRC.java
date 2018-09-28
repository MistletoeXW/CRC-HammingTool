package com.demo.tools;

import java.math.BigInteger;

/**
 * @program: CRCTool
 * @description: CRC相关的方法
 * @author: xw
 * @create: 2018-09-27 13:56
 */
public class CRC {

    private static String hexStr =  "0123456789ABCDEF";
    private static String[] binaryArray =
            {"0000","0001","0010","0011", "0100","0101","0110","0111", "1000","1001","1010","1011", "1100","1101","1110","1111"};

    /**
     * @Description: CRC校验码的生成
     * @Param: dataStr、gxStr
     * @Return:
     * @Author: xw
     * @Date: 18-9-27
    */
    public String getCRC(String dataStr,String gxStr){

        //获取二进制帧的位数
        int dataStrLen = dataStr.length();
        //获取多项式位数
        int gxStrLen = gxStr.length();
        //将二进制的字符串变为整型
        BigInteger data = byteToBigInter(dataStr);
        //将多项式的字符串变为整型
        BigInteger gx = byteToBigInter(gxStr);
        //算出原始数据补零后的总位数
        Integer sum = dataStrLen+gxStrLen-1;
        //计算2的sum-1次幂
        BigInteger bi = new BigInteger("2");
        //将2的sum-1次幂转换为int型
        BigInteger flag = bi.pow(sum-1);
        //原始帧低位补零
        data = data.multiply(new BigInteger("2").pow(gxStrLen-1));
       //多项式低位补零,使其与补零后的帧位数一致，以便异或
        gx = gx.multiply(new BigInteger("2").pow(dataStrLen-1));
        //循环dataStrLen次
        for(int i=0; i<(dataStrLen);i++){
            //判断补零后的帧最高位为1还是零
            if((data.and(flag)).compareTo(new BigInteger("0")) !=0 ) {
                data = data.xor(gx);
                gx = gx.divide(new BigInteger("2"));
            }else {
                gx = gx.divide(new BigInteger("2"));
            }
            //flag最高位的1右移
            flag = flag.divide(new BigInteger("2"));
        }
        String crcint = Integer.toBinaryString(data.intValue());
        String str="";
        if(crcint.length()< gxStrLen-1){
            for (int i=0;i<(gxStrLen-1-crcint.length());i++)
                str = str + "0";
        }
        return str+crcint;
    }


    /**
     * @Description: CRC校验
     * @Param: data
     * @Return: true or false
     * @Author: xw
     * @Date: 18-9-27
     */
    public boolean CRCCheck(String data,String gxStr,String crc){
        String datastr = strToByte(data) + crc;
        String str="";
        for (int i=0;i<(gxStr.length()-1);i++)
            str = str + "0";
        if(getCRC(datastr,gxStr).equals(str))
            return true;
        else
            return false;
    }

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
