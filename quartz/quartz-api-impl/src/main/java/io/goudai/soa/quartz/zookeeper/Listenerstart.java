package io.goudai.soa.quartz.zookeeper;

import io.goudai.soa.core.holder.SpringContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;


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
