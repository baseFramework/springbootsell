package com.demo.sell.repository;

import com.demo.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    /**
     * 根据订单ID 查询订单数据
     * @param orderId
     * @return
     */
    List<OrderDetail> findByOrderId(String orderId);
}
