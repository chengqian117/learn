package com.fy.cq.user.modules.test;


import com.fy.cq.common.utils.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {

    @Autowired
    RestTemplate restTemplate;
    @GetMapping("test")
    public String test(){
        String s="";
        s=restTemplate.getForObject("http://"+ RestConstants.ssqRest +"/test/rt",String.class);
//        restTemplate.getForEntity();
        return s;
    }
}
