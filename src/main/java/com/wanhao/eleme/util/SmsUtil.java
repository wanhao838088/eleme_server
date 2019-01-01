package com.wanhao.eleme.util;

import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.wanhao.eleme.bean.JsonResult;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Created by LiuLiHao on 2019/1/1 0001 上午 11:40
 * @author : LiuLiHao
 * 描述： 短信发送工具类
 */
public class SmsUtil {
    /**响应成功*/
    private static final String SUCCESS = "000000";
    private static CCPRestSmsSDK restAPI = null;

    static {
        restAPI = new CCPRestSmsSDK();
        restAPI.init("app.cloopen.com", "8883");
        restAPI.setAccount(SystemConstants.ACCOUNT_SID, SystemConstants.AUTH_TOKEN);
        restAPI.setAppId(SystemConstants.APPID);
    }

    /**
     * 给指定手机号发送验证码
     * @param mobile 手机号
     * @return 验证码
     */
    public static JsonResult send(String mobile){
        String code = genRandom(6);
        HashMap<String, Object> result = null;
        result = restAPI.sendTemplateSMS(mobile,"1" ,new String[]{code,"5"});

        if(SUCCESS.equals(result.get("statusCode"))){

            //正常返回输出data包体信息（map）
            HashMap<String,Object> data = (HashMap<String, Object>) result.get("data");
            Set<String> keySet = data.keySet();
            for(String key:keySet){
                Object object = data.get(key);
                System.out.println(key +" = "+object);
            }
            return new JsonResult(0,code);
        }else{
            //异常返回输出错误码和错误信息
            System.out.println("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
            return new JsonResult(1,(String) result.get("statusMsg"));
        }
    }

    /**
     * 生成指定位数的随机数
     * @param pos 几位数 例如6位
     * @return 随机字符串
     */
    private static String genRandom(int pos){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<pos;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
