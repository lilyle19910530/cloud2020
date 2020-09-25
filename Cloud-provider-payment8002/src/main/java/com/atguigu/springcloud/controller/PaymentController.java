package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entity.CommonResult;
import com.atguigu.springcloud.entity.Payment;
import com.atguigu.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/payment")
@Slf4j
public class PaymentController {

    @Resource
    IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        log.info("***********************查询成功，id为：{}", payment.getId());
        if (payment != null) {
            return new CommonResult(200, "查询数据库成功!!!,端口号:" + serverPort, payment);
        } else {
            return new CommonResult(444, "查询数据失败!!!,端口号:" + serverPort);
        }

    }

    @PostMapping(value = "/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("***********************插入成功:{}", result);
        if (result > 0) {
            return new CommonResult(200, "插入数据库成功!!!,端口号:" + serverPort, payment);
        } else {
            return new CommonResult(444, "插入数据失败!!!,端口号:" + serverPort);
        }

    }

    @GetMapping(value = "/discovery")
    public Object discovery(@PathParam("serviceId") String serviceId) {
        log.info("端口号为: "+serverPort);
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("***element: {}", element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        for (ServiceInstance serviceInstance : instances) {
            log.info("{}\t{}\t{}\t{}", serviceInstance.getInstanceId(), serviceInstance.getHost(), serviceInstance.getPort(), serviceInstance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/lb")
    public String lb () {
        return serverPort;

    }


    @RequestMapping(value = "/fengn/timeout")
    public String paymentFeignTimeout(){
        try {
            Thread.sleep(3000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return serverPort;
    }

}
