package com.demo.controller;

import com.demo.OV.Result;
import com.demo.service.HamingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @program: CRCTool
 * @description:
 * @author: xw
 * @create: 2018-09-28 16:19
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/Ham")
@Api(description = "Ham")
public class HamApi {

    @Resource
    private HamingService hamingService;

    @PostMapping("/getHam")
    @ApiOperation(value = "获取海明码", httpMethod = "POST")
    public Result getHaming(@RequestParam(value = "dataStr") String dataStr){
        return hamingService.getHam(dataStr);
    }

    @PostMapping("/checkHam")
    @ApiOperation(value = "纠错海明码", httpMethod = "POST")
    public Result checkHaming(@RequestParam(value = "dataStar") String dataStr){
        return hamingService.checkHam(dataStr);
    }

}
