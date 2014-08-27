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

import com.alibaba.fastjson.JSON;

/**
 * 支持JSONP的Fastjson的消息转换器
 * @author liuyi
 *
 */
public class FastJsonHttpMessageConverter extends com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter {

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
    		
    	if (obj instanceof JSONPObject) {
            JSONPObject jsonp = (JSONPObject) obj;
            OutputStream out = outputMessage.getBody();
            String text = jsonp.getFunction() + "(" + JSON.toJSONString(jsonp.getJson(), getFeatures()) + ")";
            System.out.println(text);
            byte[] bytes = text.getBytes(getCharset());
            out.write(bytes);
        } else {
            super.writeInternal(obj, outputMessage);
        }
    }
    
}
