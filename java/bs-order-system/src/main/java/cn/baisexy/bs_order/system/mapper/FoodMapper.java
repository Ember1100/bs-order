package cn.baisexy.bs_order.system.mapper;

import cn.baisexy.bs_order.api.entity.Food;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface FoodMapper {
    int deleteByPrimaryKey(Integer fId);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer fId);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    List<Food> getAll();

    List<Food> getAll2();

    List<Food> getFoodsByType(Integer cId);

    @Select("select f_inventory from food where f_id = #{fId}")
    Integer getInventory(Integer fId);

    @Update("update food set f_amount = f_amount-#{num} where f_id = #{fId}")
    int updateInventory(@Param("fId") Integer fId,@Param("num") Integer num);

    Food findById(Integer fId);

    List<Food> getLimitFood(@Param("lo") int lo, @Param("hi") int hi);
}