package com.pdl.blog.controller;

import com.pdl.blog.message.ResMessage;
import com.pdl.blog.pojo.Category;
import com.pdl.blog.service.CategoryService;
import com.pdl.blog.token.Token;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
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

    @ApiOperation(value = "根据id查找category")
    @GetMapping("/getById")
    public Category getCategoryById(@RequestParam("id") int id) throws Exception {
        //调用service
        return categoryService.getCategoryById(id);
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
    @PutMapping("/add")
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

    @ApiOperation(value = "删除category")
    @ApiImplicitParam(name = "tag", value = "需要删除的category",dataType = "String", required = true)
    @PostMapping("/delete")
    public ResMessage deleteCategory(@RequestBody String tag){
        ResMessage resMessage = new ResMessage();
        //调用service
        int i = categoryService.deleteCategoryByTag(tag);
        if(i == 0){
            resMessage.setMessage("删除失败，不存在该目录");
            resMessage.setSuccess(false);
            return resMessage;
        }

        resMessage.setSuccess(true);
        resMessage.setMessage("删除成功");
        return resMessage;
    }

}
