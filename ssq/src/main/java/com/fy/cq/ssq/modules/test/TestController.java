package com.fy.cq.ssq.modules.test;

import com.fy.cq.common.utils.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
@Profile("test")
@RefreshScope
public class TestController {

    @Value("${ttt}")
    private String ttt;

    @GetMapping("ttt")
    public String ttt(){
        return ttt;
    }
}
