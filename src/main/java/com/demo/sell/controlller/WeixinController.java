package com.demo.sell.controlller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weixin")
@Slf4j
@CrossOrigin
public class WeixinController {

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法！！");
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx199c36f84e8b28fb&secret=740573d44c1700eb731164914ffd678c&code="+code+"&grant_type=authorization_code";

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class);
        log.info("response={}",response);

    }
}
