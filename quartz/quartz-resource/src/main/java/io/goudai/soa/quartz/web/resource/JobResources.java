package io.goudai.soa.quartz.web.resource;

import com.alibaba.dubbo.config.annotation.Reference;
import io.goudai.soa.common.context.SoaContext;
import io.goudai.soa.common.restful.util.BeanUtil;
import io.goudai.soa.quartz.api.manger.QuartzSoaManager;
import io.goudai.soa.quartz.web.entity.Job;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
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
		public SoaContext insert(@ModelAttribute Job job,HttpServletRequest request) throws Exception {
			final SoaContext context = SoaContext.newSoaContext(JOBSERVICE, "addJob");
			context.setAttr(BeanUtil.getInstance().objectToMap(job));
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
				SoaContext context = SoaContext.newSoaContext(JOBSERVICE, "upload");
				context.addAttr("fileName", file.getOriginalFilename());
				context.addAttr("file", file.getBytes());
			return soaManager.invokeNoTx(context);
		}
		
		
		
		
}
