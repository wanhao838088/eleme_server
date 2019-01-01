package com.wanhao.eleme.app;

import com.fcibook.quick.http.QuickHttp;

/**
 * Created by LiuLiHao on 2018/12/9 0009 下午 03:36.
 * 描述：测试运行获取接口数据
 * 作者： LiuLiHao
 */
public class RunTest {

    public static void main(String[] args) {

        String res = new QuickHttp()
                .url("http://openapi.ele.me/v2/restaurants/?consumer_key=7284397484&sig=e76dfee7276f0c7a382b4f0dbdad802a95c642aa&timestamp=1374908054")
                .get()
                .text();
        System.out.println(res);
    }
}
