package com.yqbaba.test.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/kafka")
public class KafkaTestController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/test")
    public String test(ModelMap map, @RequestParam("data") String data) {
        kafkaTemplate.send("myTopic", 0, "key", data);
        return "common/json_success";
    }

}
