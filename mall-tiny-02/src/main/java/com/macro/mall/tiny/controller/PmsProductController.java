package com.macro.mall.tiny.controller;

import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.mbg.model.PmsProduct;
import com.macro.mall.tiny.service.PmsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "PmsProductController" , description = "商品类型管理")
@Controller
@RequestMapping("/product")
public class PmsProductController {

    @Autowired
    private PmsProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductController.class);

    @ApiOperation("获取所有商品列表")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsProduct>> getProductList() {
        return CommonResult.success(productService.listAllProduct());
    }

    @ApiOperation("添加商品")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createProduct(@RequestBody PmsProduct pmsProduct) {
        CommonResult commonResult;
        int count = productService.createProduct(pmsProduct);
        if (count == 1) {
            commonResult = CommonResult.success(pmsProduct);
            LOGGER.debug("createBrand success:{}", pmsProduct);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("createBrand failed:{}", pmsProduct);
        }
        return commonResult;
    }

    @ApiOperation("更新指定id商品信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProduct(@PathVariable("id") Long id, @RequestBody PmsProduct pmsProductDto, BindingResult result) {
        CommonResult commonResult;
        int count = productService.updateProduct(id, pmsProductDto);
        if (count == 1) {
            commonResult = CommonResult.success(pmsProductDto);
            LOGGER.debug("updateProduct success:{}", pmsProductDto);
        } else {
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("updateProduct failed:{}", pmsProductDto);
        }
        return commonResult;
    }

    @ApiOperation("删除指定id的商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteProduct(@PathVariable("id") Long id) {
        int count = productService.deleteProduct(id);
        if (count == 1) {
            LOGGER.debug("deleteProduct success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteProduct failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

    @ApiOperation("分页查询商品列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<PmsProduct>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1")
                                                          @ApiParam("页码") Integer pageNum,
                                                          @RequestParam(value = "pageSize", defaultValue = "3")
                                                          @ApiParam("每页数量") Integer pageSize) {
        List<PmsProduct> productList = productService.listProduct(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(productList));
    }

    @ApiOperation("获取指定id的商品详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<PmsProduct> product(@PathVariable("id") Long id) {
        return CommonResult.success(productService.getProduct(id));
    }
}
