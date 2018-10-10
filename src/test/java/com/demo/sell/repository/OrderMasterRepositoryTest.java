package com.demo.sell.repository;

import com.demo.sell.dataobject.OrderMaster;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private  final  String OPENID = "1100012";

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12112256");
        orderMaster.setBuyerName("小张");
        orderMaster.setBuyerAddress("襄州市XX区");
        orderMaster.setBuyerPhone("13212211112");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(52.2));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest request = new PageRequest(0,10);
        Page<OrderMaster> results =  repository.findByBuyerOpenid(OPENID,request);
        System.out.println(results.getTotalElements());
        Assert.assertNotEquals(0,results.getTotalPages());
    }
}