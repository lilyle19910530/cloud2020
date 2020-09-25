package com.atguigu.springcloud.service.impl;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.service.PaymentService;
import com.atguigu.springcloud.wapper.PaymentWapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentWapper paymentWapper;

    public CommonResult getPaymentById(Long id){
        return paymentWapper.getPaymentById(id);
    }

    @Override
    public String paymentFeignTimeout() {
        return paymentWapper.paymentFeignTimeout();
    }

}
