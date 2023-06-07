package com.macro.mall.tiny.service;

import com.macro.mall.tiny.mbg.model.PmsProduct;

import java.util.List;

/**
 * PmsProductService
 * Created by qiumd on 2023/6/6.
 */
public interface PmsProductService {
    List<PmsProduct> listAllProduct();

    int createProduct(PmsProduct product);

    int updateProduct(Long id, PmsProduct product);

    int deleteProduct(Long id);

    List<PmsProduct> listProduct(int pageNum, int pageSize);

    PmsProduct getProduct(Long id);
}
