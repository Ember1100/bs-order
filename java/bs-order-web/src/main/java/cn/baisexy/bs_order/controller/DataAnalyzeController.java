package cn.baisexy.bs_order.controller;

import cn.baisexy.bs_order.api.pojo.charts.ChartsOption;
import cn.baisexy.bs_order.api.pojo.send.DataReturn;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.system.service.OrderService;
import cn.baisexy.bs_order.system.service.impl.DataAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/dataAnalyze")
public class DataAnalyzeController {
    @Autowired
    OrderService orderService;
    @Autowired
    DataAnalyzeService dataAnalyzeService;

    //销量
    @RequestMapping("/totalSale")
    public DataReturn totalSale(@RequestParam(required = false) String begin, @RequestParam(required = false) String end) {
        System.out.println(begin);
        System.out.println(end);
        ChartsOption saleData = dataAnalyzeService.getSaleData(begin, end);
        return new DataReturn(new ArrayList() {{
            add(saleData);
        }}, ReturnCode.SUCCESS, null);
    }


    //收入
    @RequestMapping("/income")
    //练一下converter
    public DataReturn income(@RequestParam String beginDate, @RequestParam String endDate) {
        System.out.println(beginDate);
        System.out.println(endDate);
//        List<Orders> byDate = orderService.findByDate(beginDate, endDate);
//        ChartsOption chartsOption = GenerateChartsOption.generateIncomeData(byDate);
        ChartsOption incomeData = dataAnalyzeService.getIncomeData(beginDate,endDate);
        return new DataReturn(new ArrayList() {{
            add(incomeData);
        }}, ReturnCode.SUCCESS, null);
    }


}
