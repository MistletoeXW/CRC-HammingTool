package com.demo.controller;

import com.demo.OV.Result;
import com.demo.service.CRCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: CRCTool
 * @description:
 * @author: xw
 * @create: 2018-09-27 22:51
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/CRC")
@Api(description = "CRC")
public class CRCApi {

    @Resource
    private CRCService crcService;

    @PostMapping("/getCRC")
    @ApiOperation(value = "获取CRC", httpMethod = "POST")
    public Result getCrc(@RequestParam(value = "data") String data,
                         @RequestParam(value = "gxStr") String gxStr){
        return crcService.getCrcStr(data,gxStr);
    }

    @PostMapping("/checkCRC")
    @ApiOperation(value = "CRC校验",httpMethod = "POST")
    public Result checkCrc(@RequestParam(value = "data")String data,
                           @RequestParam(value = "gxStr")String gxStr,
                           @RequestParam(value = "crc")String crc){
        return crcService.checkCRC(data,gxStr,crc);
    }


}
