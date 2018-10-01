package com.demo.sell.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.sell.dataobject.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository  extends JpaRepository<ProductCategory,Integer>{

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
