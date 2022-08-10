package com.example.demo.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executors;

@RestController
public class test {
    @Value("${server.port}")
    private Integer port;
    @RequestMapping("port")
    public Integer tesdt(){

        return port+1;
    }
}
