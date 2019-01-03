package com.pdl.blog.controller;

import com.pdl.blog.message.ResMessage;
import com.pdl.blog.pojo.Category;
import com.pdl.blog.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "查找category")
    @GetMapping("/get")
    public List<Category> getCategory(){
        //调用service
        return categoryService.getCategory();
    }

    @ApiOperation(value = "更新category")
    @ApiImplicitParam(name = "category", value = "需要更新的category", dataType = "Category", required = true)
    @PostMapping("/update")
    public ResMessage updateCategory(@RequestBody Category category){
        ResMessage resMessage = new ResMessage();
        //调用service
        int i = categoryService.updateCategory(category);
        if( i == 1){
            resMessage.setMessage("更新成功");
            resMessage.setSuccess(true);
            return resMessage;
        }
        resMessage.setMessage("更新失败");
        resMessage.setSuccess(false);
        return resMessage;
    }

    @ApiOperation(value = "添加category")
    @ApiImplicitParam(name = "tag", value = "需要添加的category",dataType = "String", required = true)
    @PostMapping("/add")
    public ResMessage addCategory(@RequestBody String tag){
        ResMessage resMessage = new ResMessage();
        //调用service
        int i = categoryService.addCategory(tag);
        if(i == 0 ){
            resMessage.setSuccess(false);
            resMessage.setMessage("已存在该目录");
            return resMessage;
        }
        resMessage.setSuccess(true);
        resMessage.setMessage("添加成功");
        return resMessage;

    }

}
