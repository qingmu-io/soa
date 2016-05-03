package io.goudai.soa.quartz.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;


public class JobListener implements Watcher {
	// Zookeeper实例
	private ZooKeeper zk;
	private static final int TIMIOUT = 5000;// 超时时间
	public   String path;

	public JobListener(String hosts,String path) {
		try {
			this.path = path;
			zk = new ZooKeeper(hosts, TIMIOUT, this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * 写入或更新 数据
	 * 
	 * @param path
	 *            写入路径
	 * @param data
	 *            写入的值
	 * **/
	public void addOrUpdateData(String path, byte [] data) throws Exception {
		Stat stat = zk.exists(path, false);
		if (stat == null) {
			zk.create(path, data, Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);
			System.out.println("新建，并写入数据成功.. ");
		} else {
			zk.setData(path, data, -1);
			System.out.println("更新成功!");
		}
	}

	/**
	 * 读取数据
	 * 
	 * @return 读取数据的内容
	 * 
	 * **/
	public String readData() throws Exception {
		String s = new String(zk.getData(path, this, null));
		return s;
	}

	/**
	 * 关闭zookeeper连接 释放资源
	 * 
	 * **/
	public void close() {
		try {
			if(zk!=null)
			zk.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void process(WatchedEvent event) {
		try {
			if (event.getType() == Event.EventType.NodeDataChanged) {
				String readData = readData();
				if(readData != null && !"".equals(readData)){
					System.out.println(readData +"=============================");
				}else{
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}