package com.demo.sell.service;

import com.demo.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * 类目接口
 */
public interface CategoryService {

  ProductCategory findOne(Integer categoryId);

  List<ProductCategory> findAll();

  List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

  ProductCategory save(ProductCategory productCategory);
}

