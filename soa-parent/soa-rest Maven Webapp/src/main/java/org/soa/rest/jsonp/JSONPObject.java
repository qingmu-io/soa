package org.soa.rest.jsonp;

/**
 * Fastjson的JSONP消息对象
 * @author liuyi
 *
 */
public class JSONPObject {

    private String function;//JSONP回调方法
    private Object json;//真正的Json对象

    public JSONPObject(String function, Object json) {
        this.function = function;
        this.json = json;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
}
