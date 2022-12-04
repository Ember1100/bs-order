package cn.baisexy.bs_order.controller;

import cn.baisexy.bs_order.api.entity.Category;
import cn.baisexy.bs_order.api.entity.Food;
import cn.baisexy.bs_order.api.pojo.send.DataReturn;
import cn.baisexy.bs_order.api.pojo.send.ReturnCode;
import cn.baisexy.bs_order.api.pojo.send.TableFood;
import cn.baisexy.bs_order.api.pojo.send.TableReturn;
import cn.baisexy.bs_order.system.service.CategoryService;
import cn.baisexy.bs_order.system.service.FoodService;
import cn.baisexy.bs_order.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/get")
    public List find() {
        List<Category> all = categoryService.findAll();
        return all;
    }

    String picPath = "images/food";

    String realPath = "E:\\images\\";

    //用户看的
    @GetMapping("/findAllFood")
    public ResultUtil findAll(HttpServletRequest request) {
        if (categoryService == null) {
            throw new NullPointerException("缺少categoryService");
        }
        List<TableFood> result = new ArrayList<>();
        List<Category> all = categoryService.findAll();
        System.out.println(all);
        for (Category category : all) {
            TableFood tableFood = new TableFood(category);
            for (Food food : category.getFoods()) {
                tableFood.addFood(food);
            }
            result.add(tableFood);
        }
        return new ResultUtil(all, ReturnCode.SUCCESS);
    }


    //管理员看的
    @GetMapping("/findAllFoods")
    public TableReturn findAllFoods(HttpServletRequest request) {
        List<TableFood> result = new ArrayList<>();
        List<Category> all = categoryService.findAll();
        System.out.println(all);
        for (Category category : all) {
            TableFood tableFood = new TableFood(category);
            for (Food food : category.getFoods()) {
                tableFood.addFood(food);
            }
            result.add(tableFood);
        }
        return new TableReturn(result, ReturnCode.SUCCESS);
    }


    @GetMapping("/findAllFoods2")
    public TableReturn findAllFoods2(HttpServletRequest request) {
        List<TableFood> result = new ArrayList<>();
        List<Food> all2 = foodService.findAll2();
        List<Category> all21 = categoryService.findAll2();
        for (Category category : all21) {
            TableFood tableFood = new TableFood(category);
            for (Food food : all2) {
                if (food.getFCId()==category.getCId()) {
                    tableFood.addFood(food);
                }
            }
            result.add(tableFood);
        }
        return new TableReturn(result, ReturnCode.SUCCESS);
    }





    @GetMapping("getFoodById")
    public DataReturn getFoodById(Integer fid) {
        Food byId = foodService.findById(fid);
        return new DataReturn(ReturnCode.SUCCESS, new ArrayList() {{
            add(byId);
        }});
    }

    @RequestMapping("/upload")
    public DataReturn upload(@RequestParam("picture") MultipartFile pic, HttpServletRequest request) {
        System.out.println("i am in");
        File filePath = new File(realPath);
        if (!filePath.exists() && !filePath.isDirectory()) {
            filePath.mkdir();
        }
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "-" + pic.getOriginalFilename();
        try {
            pic.transferTo(new File(realPath, fileName));
            return new DataReturn(ReturnCode.SUCCESS, new ArrayList() {{
                add(picPath + "/" + fileName);
            }});
        } catch (IOException e) {
//            e.printStackTrace();
            return new DataReturn(ReturnCode.FAIL);
        }
    }


    @RequestMapping("/saveFood")
    public DataReturn updateFood(@RequestBody Food food) {
        int i = foodService.save(food);
        if (i == 1) new DataReturn(ReturnCode.SUCCESS);
        return new DataReturn(ReturnCode.FAIL);
    }

    @GetMapping("/deleteFood")
    public DataReturn delFood(Integer fid) {
//        System.out.println(fid);
        int i = foodService.del(fid);
        if (i == 1) new DataReturn(ReturnCode.SUCCESS);
        return new DataReturn(ReturnCode.FAIL);
    }

    @PostMapping("/updateCategory")
    public ResultUtil updateCategory(@RequestBody Category category) {
        int i = categoryService.update(category);
        if (i == 1) new ResultUtil<>(ReturnCode.SUCCESS);
        return new ResultUtil(ReturnCode.FAIL);
    }


    @PostMapping("/addCategory")
    public ResultUtil addCategory(@RequestBody Category category) {
        if (category==null) new ResultUtil(ReturnCode.FAIL);
        int i = categoryService.add(category);
        if (i == 1) new ResultUtil<>(ReturnCode.SUCCESS);
        return new ResultUtil(ReturnCode.FAIL);
    }

    @PostMapping("/deleteCategory")
    public ResultUtil delCategory(Integer cid) {
        if (cid==null) new ResultUtil(ReturnCode.FAIL);
        int i = categoryService.del(cid);
        if (i == 1) new ResultUtil<>(ReturnCode.SUCCESS);
        return new ResultUtil(ReturnCode.FAIL);
    }


}
