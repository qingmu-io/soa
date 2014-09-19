package org.soa.quartz.api.impl;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Stack;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.quartz.Job;
import org.quartz.JobExecutionException;
import org.soa.logger.SoaLogger;
import org.soa.quartz.core.QuartzManger;

public class JdkCompiler   {

	public final String basePath = System.getProperty("user.dir")+File.separator+"src";
	
	
	public void compile(String code,String className){
		
		String pack = "";
		final String[] split = className.split("\\.");
		String clazz = split[split.length-1];
		for(int i=0;i<split.length-1;i++){
			pack+=split[i]+File.separator;
		}
		
	    String filePath = basePath+File.separator+pack;  
	    File f = new File(filePath);  
	    if(!f.exists())
	    	f.mkdirs();
	    
	    final String pathname = filePath+clazz+".java";
		f = new File(pathname);
		try (FileWriter fw = new FileWriter(f);){
			fw.write(code);
			fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	      
	    //获取jdk编译器  
	    JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();  
	    StandardJavaFileManager fileMgr = null;
	    try{
	    	fileMgr = compiler.getStandardFileManager(null, null, null);  
	        final Iterable<? extends JavaFileObject> javaFileObjects = fileMgr.getJavaFileObjects(pathname);  
		    //编译
		    compiler.getTask(null, fileMgr, null, null, null, javaFileObjects).call();  
	    }catch (Exception e) {
	    	throw new RuntimeException("编译失败");
		}finally{
			if(fileMgr!=null)
				try {
					fileMgr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	public void loadClass(){
		try {
			 // 例如/usr/java/classes下有一个test.App类，则/usr/java/classes即这个类的根路径，而.class文件的实际位置是/usr/java/classes/test/App.class
			 File clazzPath = new File(basePath);
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
//			 				String className = subFile.getAbsolutePath();
//			 				className = className.substring(clazzPathLen, className.length() - 6);
//			 				className = className.replace(File.separatorChar, '.');
			 				// 加载Class类
//			 				final Job newInstance = (Job)Class.forName(className).newInstance();
//			 				SoaLogger.debug(QuartzManger.class,"读取应用程序类文件[class={"+className+"}]");
			 			}
			 		}
			 	}
			 } 
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JobExecutionException {
		String src = "package org.soa.quartz.api.job;"+
"public class Job5 implements org.quartz.Job {"+
"public void execute(org.quartz.JobExecutionContext context)"+
		"throws org.quartz.JobExecutionException {"+
		"System.out.println(\"我是动态添加的jobss\");"+
"}"+
"}";
		final JdkCompiler jdkCompiler = new JdkCompiler();
		jdkCompiler.compile(src, "org.soa.quartz.api.job.Job5");
		jdkCompiler.loadClass();
		final Job job = (Job)Class.forName("org.soa.quartz.api.job.Job4").newInstance();
		final Job job5 = (Job)Class.forName("org.soa.quartz.api.job.Job5").newInstance();
		job.execute(null);
		job5.execute(null);
		
//		new JdkCompiler().loadClass();
	}


}
