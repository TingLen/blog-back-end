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

    /**
     * @param name
     * 1.先查找是否存在同名category，若存在则返回错误
     * 2.不存在再调用添加sql
     */
    public int addCategory(String tag){
        CategoryExample example = new CategoryExample();
        //查询是否已经存在
        example.or().andTagEqualTo(tag);
        List<Category> categoryList = categoryMapper.selectByExample(example);
        if(categoryList.size() > 0){
            return 0;
        }
        //调用添加方法
        Category category = new Category();
        category.setTag(tag);
        return categoryMapper.insert(category);
    }

}
