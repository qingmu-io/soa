/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.soa.rest.jsonp;

import java.io.IOException;
import java.io.OutputStream;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

/**
 * 支持JSONP的Fastjson的消息转换器
 * @author liuyi
 *
 */
public class FastJsonHttpMessageConverter extends com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    		System.out.println("========================================================");
    	if (obj instanceof JSONPObject) {
            JSONPObject jsonp = (JSONPObject) obj;
            OutputStream out = outputMessage.getBody();
//            String text = jsonp.getFunction() + "(" + JSON.toJSONString(jsonp.getJson(), getFeatures()) + ")";
            super.writeInternal(jsonp.getJson(), outputMessage);
//            out.write(bytes);
        } else {
        	super.writeInternal(obj, outputMessage);
        }
    }
    public FastJsonHttpMessageConverter() {
		System.out.println("init");
	}
    
}
