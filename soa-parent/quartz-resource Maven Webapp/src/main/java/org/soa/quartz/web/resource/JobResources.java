package org.soa.quartz.web.resource;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.soa.common.context.SoaContext;
import org.soa.common.restful.util.MapUtil;
import org.soa.quartz.api.manger.QuartzSoaManager;
import org.soa.quartz.web.entity.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.alibaba.dubbo.config.annotation.Reference;
@Controller
@RequestMapping("/quartz/v1")
public class JobResources {
		private final static String JOBSERVICE = "quartzService";
		private final static String PAGE = "page";
		private final static String UPDATE = "update";
		
		
		@Reference(version="1.0.0",interfaceClass=QuartzSoaManager.class,timeout=2000,check=true,lazy=false)
		private QuartzSoaManager soaManager;
		
		@ResponseBody
		@RequestMapping(value="/jobs/{page}/{limit}",method=RequestMethod.GET)
		public SoaContext page(@PathVariable("page") int page,@PathVariable(value="limit")int limit,HttpServletRequest request){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, PAGE);
			Map<String, String[]> parameterMap = request.getParameterMap();
			if(parameterMap!=null){
				for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
					context.addAttr(entry.getKey(), entry.getValue()[0]);
				}
			}
			context.setPage(page);
			return soaManager.invoke(context);
		}
		
		@ResponseBody
		@RequestMapping(value="/jobs",method=RequestMethod.POST)
		public SoaContext insert(@ModelAttribute Job job,HttpServletRequest request){
			final SoaContext context = SoaContext.newSoaContext(JOBSERVICE, "addJob");
			context.setAttr(MapUtil.mapUtil.objToMap(job));
			context.addAttr("src", request.getParameter("src"));
			return soaManager.invoke(context);
		}
		
		@ResponseBody
		@RequestMapping(value="/jobs",method=RequestMethod.PUT)
		public SoaContext update(@ModelAttribute Job job){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, UPDATE);
			return soaManager.invoke(context);
		}
		
		
		@ResponseBody
		@RequestMapping(value="/jobs/{id}",method=RequestMethod.DELETE)
		public SoaContext update(@PathVariable("id") int id){
			SoaContext context = SoaContext.newSoaContext(JOBSERVICE, UPDATE);
			return soaManager.invoke(context);
		}
		@ResponseBody
		@RequestMapping(value="/upload",method=RequestMethod.POST)
		public SoaContext uploadJavaFile(@RequestParam("file")MultipartFile file) throws IOException{
				SoaContext context = SoaContext.newSoaContext(JOBSERVICE, "dynamicAddJob");
				context.addAttr("jobClassName", file.getOriginalFilename());
				context.addAttr("jobClass", file.getBytes());
				
			return context;
		}
		
		
		
		
}
