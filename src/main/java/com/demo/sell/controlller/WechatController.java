package com.demo.sell.controlller;

import com.demo.sell.enums.ResultEnum;
import com.demo.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import java.net.URLEncoder;

@Controller
@RequestMapping("/wechat")
@Slf4j
@CrossOrigin
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl){

        //1.配置
        //2.调用方法
        String url = "http://sellvita.natapp1.cc/sell/wechat/userInfo";
        String redirectUrl =  wxMpService.oauth2buildAuthorizationUrl(url,WxConsts.OAUTH2_SCOPE_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code, result={}",redirectUrl);

        return "redirect:" + redirectUrl;

    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                         @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try{
             wxMpOAuth2AccessToken =  wxMpService.oauth2getAccessToken(code);
        }catch (WxErrorException e){
            log.error("【微信网页授权】{}",e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(),e.getError().getErrorMsg());
        }
        String openid  = wxMpOAuth2AccessToken.getOpenId();
        return "redirect:" + returnUrl + "?openid=" + openid;
    }
}
