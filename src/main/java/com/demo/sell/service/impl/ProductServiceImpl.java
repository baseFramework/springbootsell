package com.demo.sell.service.impl;

import com.demo.sell.dataobject.ProductInfo;
import com.demo.sell.dto.CartDTO;
import com.demo.sell.enums.ProductStatusEnum;
import com.demo.sell.enums.ResultEnum;
import com.demo.sell.exception.SellException;
import com.demo.sell.repository.ProductInfoRepository;
import com.demo.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO: cartDTOList){
           ProductInfo productInfo =  repository.findOne(cartDTO.getProductId());
           if(productInfo == null){
               throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
           }
           Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
           if( result < 0){
               throw  new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
           }
           productInfo.setProductStock(result);
           repository.save(productInfo);

        }
    }
}
