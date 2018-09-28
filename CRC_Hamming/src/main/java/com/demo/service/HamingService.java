package com.demo.service;

import com.demo.OV.Result;
import com.demo.OV.ResultTool;
import com.demo.tools.CodeTool;
import com.demo.tools.Hamming;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: CRCTool
 * @description:
 * @author: xw
 * @create: 2018-09-28 16:12
 */
@Service
public class HamingService {



    public Result getHam(String datastr){
        CodeTool codeTool = new CodeTool();
        String data = codeTool.strToByte(datastr);
        Result result;
        try{
            Hamming hamming = new Hamming();
            Map<String,String> ham = new HashMap();
            ham.put("ham",hamming.getHaiMingCode(data));
            ham.put("byte",data);
            result = ResultTool.success(ham);
        }catch (Exception e){
            result = ResultTool.error("生成海明码失败！");
        }
        return result;
    }
}
