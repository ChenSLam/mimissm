package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class ProductInfoAction {
    //每页显示的记录数
    public static final int PAGE_SIZE = 5;


    //切记:在界面层种,一定会有业务逻辑层的对象
    @Autowired
    ProductInfoService productInfoService;
    //显示全部商品不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }

    //显示第一页的5条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){

        //得到第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",info);
        return "product";
    }
}
