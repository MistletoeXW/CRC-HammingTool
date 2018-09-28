package com.demo.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: CRCTool
 * @description: 海明码相关的方法
 * @author: xw
 * @create: 2018-09-27 13:57
 */
public class Hamming {

    /**
     * @Description: 生成海明码
     * @Param: 
     * @Return: 
     * @Author: xw
     * @Date: 18-9-28
     */
    public String getHaiMingCode(String src) {
        int rLen = getRLen(src);
        String[] src1 = src.split("");
        List<String> src2 = new ArrayList<String>();
        for (String s : src1) {
            src2.add(s);
        }

        int[][] chCode = new int[rLen][3];
        List<Integer> CheckNum = initialCheck(rLen, chCode);

        int[] result = new int[rLen + src.length() + 1];
        int count = 0;
        for (int i = 1; i < result.length; i++) {

            if (!CheckNum.contains(i)) {
                String biNum = Integer.toBinaryString(i);
                int srcNum = Integer.parseInt(src2.get(count++));
                result[i] = srcNum;
                updateCHCode(chCode, biNum, srcNum);
            }

        }
        for (int i = 0; i < chCode.length; i++) {
            int ck = chCode[i][2] % 2;
            result[chCode[i][0]] = ck;
        }
        String HaiMing = "";
        for (int i = 1; i < result.length; i++) {
            HaiMing += result[i];
        }
        return HaiMing;

    }

    private  void updateCHCode(int[][] chCode, String biNum, int srcNum) {
        String[] bis = biNum.split("");
        for (int i = bis.length - 1, j = 0; i >= 0; i--, j++) {
            int num = Integer.parseInt(bis[i]);
            if (num != 0) {
                chCode[j][srcNum + 1]++;
            }
        }
    }

    private List<Integer> initialCheck(int rLen, int[][] chCode){
        List<Integer> CN = new ArrayList<Integer>();
        for (int i = 0; i < rLen; i++) {
            int ck = (int) Math.pow(2, i);
            CN.add(ck);
            chCode[i][0] = ck;
        }
        return CN;
    }

    private  int getRLen(String src) {
        int len = src.length();
        for (int i = 0; i < len; i++) {
            if (Math.pow(2, i) >= 1 + len + i) {
                return i;
            }
        }
        return 0;
    }

    
    /**
     * @Description: 判断接收的海明码是否出错，返回出错位位置
     * @Param: 
     * @Return: 
     * @Author: xw
     * @Date: 18-9-28
     */
    public int check(String ham){
        char [] receive = ham.toCharArray();
        int errorBit=0;
        int size=ham.length();
        int bits=codeLength(size);
        for(int j=0;j<bits;j++){
            int flag=0;
            for(int i=0;i<size;i++){
                if(canFit(i+1, bits)){
                    if(in(i+1, j, bits)){
                        flag=XOR(flag,Integer.parseInt(String.valueOf(receive[i])));
                    }
                }
            }
            int index=(int)Math.pow(2, (double)j)-1;
            flag=XOR(flag,Integer.parseInt(String.valueOf(receive[index])));
            if(flag==1){
                errorBit+=(int)Math.pow(2, (double)j);
            }
        }
        return errorBit;
    }

    /*
     * 由海明码反向推出校验码位数
     */
    private  int codeLength(int len){
        int num=0;
        while((++num)>0){
            //逆向判断
            if((double)(len)<=Math.pow(2, (double)num)){
                return num;
            }
            if(num>20){
                return -1;
            }
        }
        return -1;
    }

    /*
     * 对两个数做异或操作
     * 相等   return 0
     * 否则   return 1
     */
    private  int XOR(int x,int y){
        if((x-y)==0){
            return 0;
        }else{
            return 1;
        }
    }
    /*
     * 返回 true   or   false
     * 如果index是2的n次方   false
     * 否则  true
     */
    private  boolean canFit(int index,int checkBits){
        for(int i=0;i<checkBits;i++){
            if(index==Math.pow(2, (double)i)){
                return false;
            }
        }
        return true;
    }

    /*
     * x=a*(2*2...*2)+b*(2*2...*2)+...+c*2+d
     *     [-bits-1-]   [-bits-2-]
     *     其中常数a,b,c..等于0，1
     *     如果 2的y次方前的常数为1   return true
     *     为0   return false
     */
    private  boolean in(int x,int y,int bits){
        int dive[]=new int[bits];
        int index=0;
        for(int i=bits-1;i>=0;i--){
            if(x/(int)Math.pow(2, (double)i)>0){
                dive[index]=1;
            }else{
                dive[index]=0;
            }
            x=x%(int)Math.pow(2, (double)i);
            index++;
        }
        if(dive[bits-y-1]==1){
            return true;
        }
        return false;
    }

}
