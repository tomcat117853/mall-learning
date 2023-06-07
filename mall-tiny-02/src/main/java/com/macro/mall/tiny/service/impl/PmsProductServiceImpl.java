package com.macro.mall.tiny.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.tiny.mbg.mapper.PmsProductMapper;
import com.macro.mall.tiny.mbg.model.PmsProduct;
import com.macro.mall.tiny.mbg.model.PmsProductExample;
import com.macro.mall.tiny.service.PmsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PmsProductServiceImpl implements PmsProductService {
    @Autowired(required=false)
    private PmsProductMapper productMapper;
    @Autowired
    public List<PmsProduct> listAllProduct() {
        return productMapper.selectByExample(new PmsProductExample());
    }
    @Override
    public int createProduct(PmsProduct Product) {
        return productMapper.insertSelective(Product);
    }

    @Override
    public int updateProduct(Long id, PmsProduct Product) {
        Product.setId(id);
        return productMapper.updateByPrimaryKeySelective(Product);
    }

    @Override
    public int deleteProduct(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsProduct> listProduct(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return productMapper.selectByExample(new PmsProductExample());
    }

    @Override
    public PmsProduct getProduct(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
