package org.soa.quartz.web.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.soa.common.context.SoaContext;
import org.soa.quartz.api.manger.QuartzSoaManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("/quartz")
public class JobResources {
		
		@Reference(version="1.0.0",interfaceClass=QuartzSoaManager.class,timeout=2000,check=true,lazy=false)
		private QuartzSoaManager soaManager;
		
		@ResponseBody
		@RequestMapping("/query")
		public SoaContext page(HttpServletRequest request){
			SoaContext context = SoaContext.newSoaContext("quartzService", "page");
			Map<String, String[]> parameterMap = request.getParameterMap();
			if(parameterMap!=null){
				for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
					context.addAttr(entry.getKey(), entry.getValue()[0]);
				}
			}
			return soaManager.invokeNoTx(context);
		}
		
		
		
}
