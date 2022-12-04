package cn.baisexy.bs_order.system.service.impl;

import cn.baisexy.bs_order.api.entity.OrderDetail;
import cn.baisexy.bs_order.api.pojo.charts.ChartsOption;
import cn.baisexy.bs_order.api.pojo.charts.DateAndIncome;
import cn.baisexy.bs_order.api.pojo.charts.FoodAndAmount;

import cn.baisexy.bs_order.system.mapper.DataAnalyzeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataAnalyzeService {
    @Autowired
    DataAnalyzeMapper dataAnalyzeDao;

    //销量
    public ChartsOption getSaleData(String begin, String end) {
        final ChartsOption chartsOption = new ChartsOption();
        chartsOption.setTitle(chartsOption.new Title("各菜品的总销售量"));
        chartsOption.setLegend(chartsOption.new Legend(new ArrayList<String>() {
            {
                add("销量");
            }
        }));
//        List<Food> all = foodService.findAll();

        List<FoodAndAmount> foodAndAmounts = null;
        if (begin == null) {
            foodAndAmounts = dataAnalyzeDao.getFoodAndAmounts();
        }
        else {
            foodAndAmounts = dataAnalyzeDao.getFoodAndAmountsByDate(begin,end);
        }
        List<String> axisData = new ArrayList<>();
        final List<String> seriesData = new ArrayList<>();
        for (FoodAndAmount faa: foodAndAmounts) {
            axisData.add(faa.getFName());
            seriesData.add(faa.getAmount()+"");
        }
//        for (Food food : all) {
//            axisData.add(food.getFName());
//            seriesData.add(food.getFAmount() + "");
//        }
        chartsOption.setXAxis(chartsOption.new Axis(axisData));
        chartsOption.setYAxis(chartsOption.new Axis(null));
        chartsOption.setSeries(new ArrayList() {{
            add(chartsOption.new Series("销量", "bar", seriesData));
        }});
        return chartsOption;
    }

    //收入
    public ChartsOption getIncomeData(String begin, String end) {
        List<DateAndIncome> dateAndIncome = dataAnalyzeDao.getDateAndIncome(begin, end);
//        Map<String, Double> dateToMoney = new TreeMap<>();
//        for (Orders orders : allOrders) {
//            String date = orders.getODate();
//            if (dateToMoney.containsKey(date))
//                dateToMoney.put(date, dateToMoney.get(date) + Double.valueOf(orders.getOPrice()));
//            else
//                dateToMoney.put(orders.getODate(), Double.valueOf(orders.getOPrice()));
//        }
        ChartsOption chartsOption = new ChartsOption();
        chartsOption.setTitle(chartsOption.new Title("收入情况"));
        chartsOption.setLegend(chartsOption.new Legend(new ArrayList() {{
            add("收入");
        }}));
        List<String> axisList = new ArrayList<>();
        List<String> seriesList = new ArrayList<>();
//        for (Map.Entry<String, Double> entry : dateToMoney.entrySet()) {
//            axisList.add(entry.getKey() + "");
//            seriesList.add(entry.getValue() + "");
//        }
        for (DateAndIncome dai: dateAndIncome) {
            axisList.add(dai.getDate());
            seriesList.add(dai.getIncome()+"");
        }
        chartsOption.setYAxis(chartsOption.new Axis(null));
        chartsOption.setXAxis(chartsOption.new Axis(axisList));
        final ChartsOption.Series series = chartsOption.new Series("收入", "bar", seriesList);
        chartsOption.setSeries(new ArrayList() {{
            add(series);
        }});
        return chartsOption;

    }

    public int addOrderDetail(OrderDetail orderDetail) {
       return dataAnalyzeDao.addOrderDetail(orderDetail);
    }


}
