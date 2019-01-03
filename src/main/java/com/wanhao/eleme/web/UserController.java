package com.wanhao.eleme.web;

import com.wanhao.eleme.bean.JsonResult;
import com.wanhao.eleme.bean.user.UserInfo;
import com.wanhao.eleme.util.SystemConstants;
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
        //设置session过时时间
        session.setMaxInactiveInterval(SystemConstants.SESSION_TIMEOUT);

        if (StringUtils.isNotBlank(orgCode) && orgCode.equals(code)){
            return new JsonResult(0,"登录成功!",saveUser(session));
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
        //设置session过时时间
        session.setMaxInactiveInterval(SystemConstants.SESSION_TIMEOUT);

        if(validateCode!= null && validateCode.equals(captcha)){
            if ("admin".equals(name) && "admin".equals(pwd)){
                return new JsonResult(0,"登录成功!",saveUser(session));
            }else {
                return new JsonResult(1,"密码错误!");
            }
        }else{
            //验证码错误
            return new JsonResult(1,"验证码错误!");
        }
    }

    /**
     * 根据session获取用户信息
     * @param session
     * @return
     */
    @RequestMapping(value = "reqUserInfo")
    @ResponseBody
    public JsonResult reqUserInfo(HttpSession session){
        return new JsonResult(0,"获取成功!",session.getAttribute("user"));
    }

    /**
     * 退出登录
     * @param session
     * @return
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public JsonResult logout(HttpSession session){
        session.removeAttribute("user");
        session.invalidate();
        return new JsonResult(0,"退出成功!");
    }

    /**
     * 生成用户信息
     * @param session
     * @return
     */
    private UserInfo saveUser(HttpSession session){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("admin");
        userInfo.setId("admin");

        session.setAttribute("user",userInfo);
        return userInfo;
    }
}
