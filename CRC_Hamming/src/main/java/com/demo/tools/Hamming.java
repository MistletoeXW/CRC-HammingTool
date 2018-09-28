package com.demo.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        int rLen = getRLen(src);     //获取校验码位数
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

}
