package cn.baisexy.bs_order.api.pojo.send;


import cn.baisexy.bs_order.api.entity.Category;
import cn.baisexy.bs_order.api.entity.Food;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class TableFood {
    String id;
    Integer rid;
    String name;
    String desc;
    Integer price;
    Integer amount;
    String img;
    String type;
    List<TableFood> next = new ArrayList<>();

    public TableFood() {

    }

    public TableFood(Category category) {
        this.name = category.getCName();
        this.desc = category.getCDes();
        this.id = category.getCId() + " ";
        this.type = "category";
    }

    public boolean addFood(Food food) {
        TableFood tableFood = new TableFood();
        tableFood.setAmount(food.getFAmount());
        tableFood.setDesc(food.getFDes());
        tableFood.setName(food.getFName());
        tableFood.setPrice(food.getFPrice());
        tableFood.setId(food.getFCId() + "-" + food.getFId());
        tableFood.setRid(food.getFId());
        tableFood.setImg(food.getFImg());
        return next.add(tableFood);
    }


}
