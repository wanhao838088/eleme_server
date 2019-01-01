package com.wanhao.eleme.web;

import com.wanhao.eleme.bean.JsonResult;
import com.wanhao.eleme.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by LiuLiHao on 2019/1/1 0001 上午 11:31
 * @author : LiuLiHao
 * 描述：短信验证码发送
 */
@RequestMapping(value = "smsController")
@Controller
public class SmsController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 发送短信
     * @param phone 要发送到的手机号
     */
    @RequestMapping(value = "sendSms")
    @ResponseBody
    public JsonResult sendSms(String phone, HttpSession session){
        JsonResult result = SmsUtil.send(phone);
        session.setAttribute(phone, result.getMsg());
        redisTemplate.opsForValue().set(phone, result.getMsg());
        return result;
    }


}
