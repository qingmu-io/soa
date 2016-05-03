package io.goudai.soa.core.holder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;

/**
 * 以静态变量保存Spring ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 *
 * @author freeman
 */
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    static Logger logger = LoggerFactory.getLogger(SpringContextHolder.class);
    private static ApplicationContext applicationContext = null;

    public SpringContextHolder() {
    }

    /**
     * 取得存储在静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.info("注入ApplicationContext到SpringContextHolder:{}", applicationContext);

        if (SpringContextHolder.applicationContext != null) {
            logger.info("SpringContextHolder中的ApplicationContext被覆盖{}, 原有ApplicationContext为:{}", applicationContext, SpringContextHolder.applicationContext);
        }

        SpringContextHolder.applicationContext = applicationContext; // NOSONAR
    }

    public static String getRootRealPath() {
        String rootRealPath = "";
        try {
            rootRealPath = getApplicationContext().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            logger.warn("获取系统根目录失败");
        }
        return rootRealPath;
    }

    public static String getResourceRootRealPath() {
        String rootRealPath = "";
        try {
            rootRealPath = new DefaultResourceLoader().getResource("").getFile().getAbsolutePath();
        } catch (IOException e) {
            logger.warn("获取资源根目录失败");
        }
        return rootRealPath;
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        logger.info("清除SpringContextHolder中的ApplicationContext:", applicationContext);
        applicationContext = null;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new RuntimeException("applicaitonContext属性未注入, 请在applicationContext.xml中定义SpringContextHolder.");
        }
        ;
    }

    /**
     * 实现DisposableBean接口, 在Context关闭时清理静态变量.
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }
}