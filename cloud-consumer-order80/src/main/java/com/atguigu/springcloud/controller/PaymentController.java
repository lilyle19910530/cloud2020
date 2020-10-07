package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;

@RestController
public class PaymentController {

    //private static String PAYMENT_URL = "http://localhost:8001/";
    private static String PAYMENT_URL = "http://CLOUD-PROVIDER-PAYMENT/";


    @Resource
    RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/create")
    public ResponseEntity<CommonResult> create(Payment payment) {
        return restTemplate.postForEntity(PAYMENT_URL + "payment/create", payment, CommonResult.class);
    }

    @GetMapping(value = "/consumer/payment/get/{id}")
//    public ResponseEntity<CommonResult> getPaymentById(@PathVariable("id") Long id) {
//        return restTemplate.getForEntity(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
//    }
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "payment/get/" + id, CommonResult.class);
    }
    @GetMapping(value = "/consumer/discovery")
    public Object discovery(@PathParam("serviceId") String serviceId) {
        return restTemplate.getForEntity(PAYMENT_URL + "/payment/discovery?serviceId=" + serviceId, Object.class);
    }

    @GetMapping(value = "/consumer/payment/zipink")
    public ResponseEntity<String> zipink(){
        return restTemplate.getForEntity(PAYMENT_URL+"/payment/zipink", String.class);
    }

}
