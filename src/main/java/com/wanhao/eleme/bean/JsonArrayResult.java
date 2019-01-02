package com.wanhao.eleme.bean;

import java.util.List;

/**
 * 标准数组JSON响应消息模板
 * @author Administrator
 * @param <T>
 */
public class JsonArrayResult<T> extends JsonResult{

    public JsonArrayResult(Integer code, List<T> data) {
        super(code);
        this.data = data;
    }

    private Integer count;

    public Integer getCount() {
        if(count == null || count == 0) return this.data.size();
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
