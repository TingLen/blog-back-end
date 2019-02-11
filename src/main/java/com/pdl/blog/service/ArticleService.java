package com.pdl.blog.service;

import com.pdl.blog.dao.ArticlesMapper;
import com.pdl.blog.pojo.Articles;
import com.pdl.blog.pojo.ArticlesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticlesMapper articlesMapper;

    public List<Articles> getLastList(){
       return articlesMapper.getLastList();
    }

    public List<Articles> getList() throws ParseException {
        return articlesMapper.getList();
    }

    public int deleteArticleById(int id){
        ArticlesExample example = new ArticlesExample();
        example.or().andIdEqualTo(id);
        return articlesMapper.deleteByExample(example);
    }

    public List<Articles> getListByCid(int cid) {
        return articlesMapper.getListByCid(cid);
    }
}
