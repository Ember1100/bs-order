package cn.baisexy.bs_order.system.mapper;

import cn.baisexy.bs_order.api.entity.OrderDetail;
import cn.baisexy.bs_order.api.pojo.charts.DateAndIncome;
import cn.baisexy.bs_order.api.pojo.charts.FoodAndAmount;
import org.apache.ibatis.annotations.*;
import java.util.List;


public interface DataAnalyzeMapper {

    @Select("select sum(od_f_amount) as amount,f.f_name as fName from orderdetail od, food f where od.od_f_id = f.f_id group by od_f_id ;")
    List<FoodAndAmount> getFoodAndAmounts();


    List<FoodAndAmount> getFoodAndAmountsByDate(@Param("begin") String begin, @Param("end") String end);

    @Select("select o_date as date, sum(o_price) as income from orders where o_date between #{begin} and #{end} group by o_date")
     List<DateAndIncome> getDateAndIncome(@Param("begin") String begin, @Param("end")String end);

    @Insert("insert into orderdetail(od_id,od_o_id,od_f_id,od_f_amount) values(#{oDId,jdbcType=INTEGER}, " +
            "#{oDOId}, #{oDFId},#{oDFAmount})")
    int addOrderDetail(OrderDetail orderDetail);
}
