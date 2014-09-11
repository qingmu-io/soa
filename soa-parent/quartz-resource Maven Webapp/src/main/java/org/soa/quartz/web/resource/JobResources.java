package org.soa.quartz.web.resource;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.soa.common.context.SoaContext;
import org.soa.quartz.api.manger.QuartzSoaManager;
import org.soa.quartz.web.entity.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
@Controller
@RequestMapping("/quartz/v1")
public class JobResources {
		private final static String JOBSERVICE = "quartzService";
		private final static String PAGE = "page";
		private final static String INSERT = "insert";
		private final static String UPDATE = "update";
		
		
		@Reference(version="1.0.0",interfaceClass=QuartzSoaManager.class,timeout=2000,check=true,lazy=false)
		private QuartzSoaManager soaManager;
		
		@ResponseBody
		@RequestMapping(value="/jobs/{page}",method=RequestMethod.GET)
		public SoaContext page(HttpServletRequest request){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, PAGE);
			Map<String, String[]> parameterMap = request.getParameterMap();
			if(parameterMap!=null){
				for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
					context.addAttr(entry.getKey(), entry.getValue()[0]);
				}
			}
			return soaManager.invokeNoTx(context);
		}
		
		@ResponseBody
		@RequestMapping(value="/jobs",method=RequestMethod.POST)
		public SoaContext insert(@ModelAttribute Job job){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, INSERT);
			
			return soaManager.invoke(context);
			
		}
		
		@ResponseBody
		@RequestMapping(value="jobs",method=RequestMethod.PUT)
		public SoaContext update(@ModelAttribute Job job){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, UPDATE);
			return soaManager.invoke(context);
		}
		
		
		@ResponseBody
		@RequestMapping(value="jobs/{id}",method=RequestMethod.DELETE)
		public SoaContext update(@PathVariable("id") int id){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, UPDATE);
			return soaManager.invoke(context);
		}
		
		
		
		
}
