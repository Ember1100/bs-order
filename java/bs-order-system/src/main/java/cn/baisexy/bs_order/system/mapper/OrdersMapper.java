package cn.baisexy.bs_order.system.mapper;

import cn.baisexy.bs_order.api.entity.Orders;
import cn.baisexy.bs_order.api.pojo.charts.FoodAndAmount;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OrdersMapper {
    int deleteByPrimaryKey(Integer oId);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Integer oId);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> findOrdersByUser(Integer uId);

    @Select("select max(o_id) from orders")
    Integer getMaxId();

    int updateStatus(@Param("oStatus") String oStatus, @Param("oId") Integer oId);

    List<FoodAndAmount> getFoodAndAmounts();



    List<Orders> findOrder(@Param("begin") int begin, @Param("end") int end);
}