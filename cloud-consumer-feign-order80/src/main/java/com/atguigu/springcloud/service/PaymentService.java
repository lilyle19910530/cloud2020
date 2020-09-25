package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entity.CommonResult;

public interface PaymentService {

    public CommonResult getPaymentById(Long id);

    String paymentFeignTimeout();
}
