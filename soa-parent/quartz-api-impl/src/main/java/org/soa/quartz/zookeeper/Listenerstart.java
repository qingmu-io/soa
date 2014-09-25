package org.soa.quartz.zookeeper;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.soa.core.holder.SpringContextHolder;
import org.springframework.stereotype.Service;


@Service
public class Listenerstart {

	@PostConstruct
	public void register() throws Exception{
	new Thread(new Runnable() {
		
		@Override
		public void run() {
			Map<String, String> param = SpringContextHolder.getBean("jobZookeeperMap");
			final String path = param.get("path");
			JobListener jobListener = new JobListener(param.get("zkaddress"),path);
			try {
				jobListener.addOrUpdateData(path, new byte[0]);
				jobListener.readData();
				while(true){
					Thread.sleep(Long.MAX_VALUE);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}).start();
	}

	
	
}
