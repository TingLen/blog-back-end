package com.pdl.blog.service;

import com.pdl.blog.dao.CategoryMapper;
import com.pdl.blog.pojo.Category;
import com.pdl.blog.pojo.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getCategory(){
        CategoryExample example = new CategoryExample();
        //以id排序
        example.setOrderByClause("id");
        example.or();
        return categoryMapper.selectByExample(example);
    }

    public int updateCategory(Category category){
        return categoryMapper.updateByPrimaryKey(category);
    }

}
