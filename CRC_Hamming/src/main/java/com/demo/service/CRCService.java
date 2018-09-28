package com.demo.service;

import com.demo.OV.Result;
import com.demo.OV.ResultTool;
import com.demo.tools.CRC;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: CRCTool
 * @description:
 * @author: xw
 * @create: 2018-09-27 23:00
 */
@Service
public class CRCService {

    public Result getCrcStr(String dataStr,String gxStr){
        Result result;
        try{
            CRC Crc = new CRC();
            Map<String,String> crc = new HashMap<String, String>();
            crc.put("crc",Crc.getCRC(dataStr,gxStr));
            crc.put("byte",dataStr);
            result = ResultTool.success(crc);
        }catch (Exception e){
            result = ResultTool.error("生成CRC失败！");
        }
        return result;
    }

    public Result checkCRC(String data,String gxStr,String crcstr){
        Result result;
        try{
            CRC Crc = new CRC();
            boolean crc = Crc.CRCCheck(data,gxStr,crcstr);
            result = ResultTool.success(crc);
        }catch (Exception e){
            result = ResultTool.error("检验失败！");
        }
        return result;
    }
}
