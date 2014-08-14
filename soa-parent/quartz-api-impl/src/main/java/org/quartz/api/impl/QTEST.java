package org.quartz.api.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.api.QuartzService;
import org.soa.common.context.SoaContext;
import org.springframework.stereotype.Service;

@Service
public class QTEST {
	
	@Resource
	private QuartzService quartzService;
	
	@PostConstruct
	public void test(){
		SoaContext context = new SoaContext();
//		context.addAttr("guid", "ADFASFDKAKJasdflkjdfa");
//		context = this.get(context);
		quartzService.query(context);
		context.setAttr(context.getRows().get(0));
		quartzService.addJob(context);
	}

}
