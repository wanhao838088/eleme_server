package com.wanhao.eleme.web;

import com.wanhao.eleme.bean.JsonResult;
import com.wanhao.eleme.bean.user.UserInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by LiuLiHao on 2019/1/1 0001 下午 05:42
 * @author : LiuLiHao
 * 描述：
 */
@RequestMapping(value = "userController")
@Controller
public class UserController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 手机号验证码登录
     * @param phone 手机号
     * @param code 验证码
     * @param session
     * @return
     */
    @RequestMapping(value = "loginByPhone")
    @ResponseBody
    public JsonResult loginByPhone(String phone, String code, HttpSession session){
        //判断验证码
        String orgCode = (String) redisTemplate.opsForValue().get(phone);

        if (StringUtils.isNotBlank(orgCode) && orgCode.equals(code)){
            UserInfo userInfo = new UserInfo();
            userInfo.setName("admin");
            userInfo.setId("admin");

            return new JsonResult(0,"登录成功!",userInfo);
        }else {
            //验证码错误
            return new JsonResult(1,"验证码错误!");
        }
    }

    /**
     * 用户名密码登录
     * @param name 用户名
     * @param pwd 密码
     * @param session
     * @return
     */
    @RequestMapping(value = "loginByName")
    @ResponseBody
    public JsonResult loginByName(String name, String pwd,String captcha, HttpSession session){
        //判断验证码
        String validateCode= (String) session.getAttribute(KaptchaController.VALIDATECODE);

        if(validateCode!= null && validateCode.equals(captcha)){
            if ("admin".equals(name) && "admin".equals(pwd)){
                UserInfo userInfo = new UserInfo();
                userInfo.setName("admin");
                userInfo.setId("admin");

                return new JsonResult(0,"登录成功!",userInfo);
            }else {
                return new JsonResult(1,"密码错误!");
            }
        }else{
            //验证码错误
            return new JsonResult(1,"验证码错误!");
        }
    }
}
