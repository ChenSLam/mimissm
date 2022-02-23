package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
    //切记:业务逻辑层种一定有数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;
    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
        //分页插件使用PageHelper工具类完成分页设置
        PageHelper.startPage(pageNum,pageSize);

        //进行PageInfo的数据封装
        //进行有条件的查询操作,必须要创建ProductInfoExample对象
        ProductInfoExample example = new ProductInfoExample();
        //设置排序,按主键降序排序.
        example.setOrderByClause("p_id desc");
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
        //将查到的集合封装到PageInfo对象中
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
