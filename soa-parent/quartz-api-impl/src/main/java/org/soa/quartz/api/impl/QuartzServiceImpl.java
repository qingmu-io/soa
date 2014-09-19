package org.soa.quartz.api.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.soa.common.context.SoaContext;
import org.soa.common.exception.QuartzException;
import org.soa.core.service.BaseService;
import org.soa.logger.SoaLogger;
import org.soa.quartz.api.QuartzService;
import org.soa.quartz.core.QuartzManger;
import org.soa.quartz.core.Status;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.compiler.support.JavassistCompiler;


@Service("quartzService")
public class QuartzServiceImpl extends BaseService implements QuartzService {
	private final static String QUARTZ = "QUARTZ";
	private static final String LOAD = "load";
	//通过作业名称统计作业个数
	private static final String COUNTQRTJOBDETAILS = "countQrtzJobDetails";
	//通过作业触发器名称统计
	private static final String COUNTQRTZCRONTRIGGERS = "countQrtzCronTriggers";
	//通过作业类统计
	private static final String COUNTJOBCLASS = "countJobClass";
	
	@Resource
	private QuartzManger quartzManger;
	
	@PostConstruct
	public void start() throws InstantiationException, IllegalAccessException, ClassNotFoundException, MalformedURLException{
		String src = "package org.soa.quartz.api.job;"+
"public class Job4 implements org.quartz.Job {"+
"public void execute(org.quartz.JobExecutionContext context)"+
		"throws org.quartz.JobExecutionException {"+
		"System.out.println(\"我是动态添加的job\");"+
"}"+
"}";
//		this.compilce(null);
		
		
		final URL resource = ClassLoader.getSystemClassLoader().getResource("");
		
		System.out.println(resource);
		
		this.quartzManger.start();
	}
	
	@Override
	public SoaContext modifyJob(SoaContext context) {
		super.update(context);
		this.quartzManger.rescheduleJob(context);
		return context;
	}

	@Override
	public SoaContext deleteJob(SoaContext context) {
		super.delete(context);
		this.quartzManger.deleteJob(context);
		return context;
	}

	@Override
	public SoaContext pauseJob(SoaContext context) {
		context.addAttr("status", Status.PAUSE);
		super.update(context);
		this.quartzManger.pauseJob(context);
		return context;
	}

	@Override
	public SoaContext resumeJob(SoaContext context) {
		context.addAttr("status", Status.RUN);
		super.update(context);
		this.quartzManger.resumeJob(context);
		return context;
	}

	@Override
	public Trigger getTrigger(SoaContext context) {
		return this.quartzManger.getTrigger(context);
	}

	@Override
	public Trigger getTriggerById(SoaContext context) {
		return this.getTrigger(super.queryStatement(context, LOAD));
	}

	@Override
	public JobDetail getJobDetail(SoaContext context) {
		return this.quartzManger.getJobDetail(context);
	}

	@Override
	public JobDetail getJobDetailById(SoaContext context) {
		return this.getJobDetail(super.queryStatement(context, LOAD));
	}

	@Override
	public SoaContext get(SoaContext context) {
		return super.queryStatement(context, LOAD);
	}

	@Override
	public SoaContext page(SoaContext context) {
		return super.queryByPage(context);
	}

	@Override
	public SoaContext pauseAll(SoaContext context) {
		this.quartzManger.pauseAll();
		context.addAttr("STATUS", -1);
		this.update(context);
		return context;
	}

	@Override
	public SoaContext resumeAll(SoaContext context) {
		this.quartzManger.resumeAll();
		context.addAttr("STATUS", 1);
		this.update(context);
		return context;
	}


	@Override
	public String getNameSpace() {
		return QUARTZ;
	}
	
	public void setQuartzManger(QuartzManger quartzManger) {
		this.quartzManger = quartzManger;
	}

	@Override
	public SoaContext addJob(SoaContext context) {
		 try {
			int count = super.count(context, COUNTQRTJOBDETAILS);
			if(count>0){
				throw new QuartzException("作业名称已经存在");
			}
			count = super.count(context, COUNTQRTZCRONTRIGGERS);
			if(count>0){
				throw new QuartzException("触发器名称已经存在");
			}
			count = super.count(context, COUNTJOBCLASS);
			if(count>0) {
				throw new QuartzException("该作业类已经在存在");
			}
			this.quartzManger.addJob(context);
			this.insert(context);
			context.getAttr().clear();
			return context;
		} catch (QuartzException e) {
			
		}
		return context;
	}

	JavassistCompiler compiler = new JavassistCompiler();
	@Override
	public SoaContext dynamicAddJob(SoaContext context) {
		//注册到classpath中
		compiler.compile(context.getStringAttr("src"), ClassLoader.getSystemClassLoader());
		 try {
				int count = super.count(context, COUNTQRTJOBDETAILS);
				if(count>0){
					throw new QuartzException("作业名称已经存在");
				}
				count = super.count(context, COUNTQRTZCRONTRIGGERS);
				if(count>0){
					throw new QuartzException("触发器名称已经存在");
				}
				count = super.count(context, COUNTJOBCLASS);
				if(count>0) {
					throw new QuartzException("该作业类已经在存在");
				}
				this.quartzManger.addJob(context);
				this.insert(context);
				context.getAttr().clear();
				return context;
			} catch (QuartzException e) {
				
			}
			return context;
	}
	
	
	
	public static void main(String[] args) throws MalformedURLException {
		String src = "package org.soa.quartz.api.job;"+
"public class Job4 implements org.quartz.Job {"+
"public void execute(org.quartz.JobExecutionContext context)"+
		"throws org.quartz.JobExecutionException {"+
		"System.out.println(\"我是动态添加的job\");"+
"}"+
"}";
		 try {
			
			    String fileName = System.getProperty("user.dir")+"\\src\\org\\soa\\quartz\\api\\job\\";  
			    File f = new File(fileName);  
			    if(!f.exists())
			    	f.mkdirs();
			    final String pathname = fileName+"Job4.java";
				f = new File(pathname);
			    FileWriter fw = new FileWriter(f);  
			    fw.write(src);  
			    fw.flush();  
			    fw.close();  
			      
			    //获取jdk编译器  
			    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
			    StandardJavaFileManager fileMgr = compiler.getStandardFileManager(null, null, null);  
			    Iterable  units = fileMgr.getJavaFileObjects(pathname);  
			    //拿到编译任务  
			    CompilationTask t = compiler.getTask(null, fileMgr, null, null, null, units);  
			    t.call();//编译  
			    fileMgr.close();  
			      
			 // 设置class文件所在根路径
			 // 例如/usr/java/classes下有一个test.App类，则/usr/java/classes即这个类的根路径，而.class文件的实际位置是/usr/java/classes/test/App.class
			 File clazzPath = new File(System.getProperty("user.dir")+"\\src");

			 // 记录加载.class文件的数量
			 int clazzCount = 0;

			 if (clazzPath.exists() && clazzPath.isDirectory()) {
			 	// 获取路径长度
			 	int clazzPathLen = clazzPath.getAbsolutePath().length() + 1;

			 	Stack<File> stack = new Stack<>();
			 	stack.push(clazzPath);

			 	// 遍历类路径
			 	while (stack.isEmpty() == false) {
			 		File path = stack.pop();
			 		File[] classFiles = path.listFiles(new FileFilter() {
			 			public boolean accept(File pathname) {
			 				return pathname.isDirectory() || pathname.getName().endsWith(".class");
			 			}
			 		});
			 		for (File subFile : classFiles) {
			 			if (subFile.isDirectory()) {
			 				stack.push(subFile);
			 			} else {
			 				if (clazzCount++ == 0) {
			 					Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			 					boolean accessible = method.isAccessible();
			 					try {
			 						if (accessible == false) {
			 							method.setAccessible(true);
			 						}
			 						// 设置类加载器
			 						URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
			 						// 将当前类路径加入到类加载器中
			 						method.invoke(classLoader, clazzPath.toURI().toURL());
			 					} finally {
			 						method.setAccessible(accessible);
			 					}
			 				}
			 				// 文件名称
			 				String className = subFile.getAbsolutePath();
			 				className = className.substring(clazzPathLen, className.length() - 6);
			 				className = className.replace(File.separatorChar, '.');
			 				// 加载Class类
			 				final Job newInstance = (Job)Class.forName(className).newInstance();
			 				SoaLogger.debug(QuartzManger.class,"读取应用程序类文件[class={"+className+"}]");
			 			}
			 		}
			 	}
			 } 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	          
	        
	}

}
