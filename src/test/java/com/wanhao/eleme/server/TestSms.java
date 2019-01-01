package com.wanhao.eleme.server;

import com.wanhao.eleme.bean.JsonResult;
import com.wanhao.eleme.util.SmsUtil;
import org.junit.Test;

/**
 * Created by LiuLiHao on 2019/1/1 0001 上午 11:54
 * @author : LiuLiHao
 * 描述：
 */
public class TestSms {

    @Test
    public void testSend(){
        JsonResult send = SmsUtil.send("18865392565");
        System.out.println("验证吗是: "+send);
    }
}
