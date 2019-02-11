package com.pdl.blog.controller;

import com.pdl.blog.message.ResMessage;
import com.pdl.blog.pojo.Articles;
import com.pdl.blog.service.ArticleService;
import com.pdl.blog.service.CategoryService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取最新5篇文章列表")
    @GetMapping("/getLastList")
    public List<Articles> getLastArticlesList(){
     return  articleService.getLastList();
    }

    @ApiOperation(value = "获取文章列表")
    @GetMapping("/getList")
    public List<Articles> getArticlesList() throws ParseException {
        return  articleService.getList();
    }

    @ApiOperation(value = "根据分类获取文章列表")
    @GetMapping("/getListByCategory")
    public List<Articles> getArticlesListByCategory(@RequestParam(value = "category") String category) {
        //查询cid
        int cid = categoryService.getCidByCategory(category);
        //根据cid查找文章列表
        return articleService.getListByCid(cid);

    }

    @ApiOperation(value = "删除文章")
    @ApiImplicitParam(name = "id", value = "文章id", dataType = "int", required = true)
    @DeleteMapping(value = "/deleteById/{id}")
    public ResMessage deleteArticleById(@PathVariable("id") int id){
        int i = articleService.deleteArticleById(id);
        ResMessage message = new ResMessage();
        if(i > 0){
            message.setSuccess(true);
            message.setMessage("删除成功");
            return message;
        }
        message.setSuccess(false);
        message.setMessage("删除失败，id不存在");
        return message;
    }



}
