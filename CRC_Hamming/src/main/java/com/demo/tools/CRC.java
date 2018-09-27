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
        Integer dataStrLen = dataStr.length();
        //获取多项式位数
        Integer gxStrLen = gxStr.length();
        //将二进制的字符串变为整型
        Integer data = Integer.parseInt(dataStr,2);
        //将多项式的字符串变为整型
        Integer gx = Integer.parseInt(gxStr,2);
        //算出原始数据补零后的总位数
        Integer sum = dataStrLen+gxStrLen-1;
        //计算2的sum-1次幂
        BigInteger bi = new BigInteger("2");
        //将2的sum-1次幂转换为int型
        Integer flag = bi.pow(sum-1).intValue();
        //原始帧低位补零
        data = data<<(gxStrLen-1);
        //多项式低位补零,使其与补零后的帧位数一致，以便异或
        gx = gx<<(dataStrLen-1);
        //循环dataStrLen次
        for(int i=0; i<(dataStrLen);i++){
            //判断补零后的帧最高位为1还是零
            if((data&flag)!=0) {
                data = data^gx;
                gx = gx>>1;
            }else {
                gx = gx>>1;
            }
            //flag最高位的1右移
            flag = flag>>1;
        }
        String crc = Integer.toBinaryString(data);

        return crc;
    }


    /**
     * @Description: CRC校验
     * @Param: data
     * @Return: true or false
     * @Author: xw
     * @Date: 18-9-27
     */
    public boolean CRCCheck(String data,String gxStr,String crc){
        String datastr = data + crc;
        if(getCRC(datastr,gxStr).equals("0"))
            return true;
        else
            return false;
    }


    /**
     *
     * @param
     * @return 二进制数组转换为二进制字符串   2-2
     */
    private  String bytes2BinStr(byte[] bArray){

        String outStr = "";
        int pos = 0;
        for(byte b:bArray){
            //高四位
            pos = (b&0xF0)>>4;
            outStr += binaryArray[pos];
            //低四位
            pos=b&0x0F;
            outStr+=binaryArray[pos];
        }
        return outStr;
    }

    /**
     *
     * @param hexString
     * @return 将十六进制转换为二进制字节数组   16-2
     */
    private  byte[] hexStr2BinArr(String hexString){
        //hexString的长度对2取整，作为bytes的长度
        int len = hexString.length()/2;
        byte[] bytes = new byte[len];
        byte high = 0;//字节高四位
        byte low = 0;//字节低四位
        for(int i=0;i<len;i++){
            //右移四位得到高位
            high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);
            low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));
            bytes[i] = (byte) (high|low);//高地位做或运算
        }
        return bytes;
    }

    /**
     * @Description: 十六进制转二进制
     * @Param:
     * @Return:
     * @Author: xw
     * @Date: 18-9-27
     */
    public String hexStr2BinStr(String hexString){
        return bytes2BinStr(hexStr2BinArr(hexString));
    }


}
