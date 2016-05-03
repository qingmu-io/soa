package io.goudai.soa.quartz.core.util;

import org.quartz.Job;


public final class ClazzUtils {
        /**
        * @Description: TODO(将类全路径字符串转换为类模板)
        * @author LiuYi
         * @param <T>
        * @date 2014年7月9日 下午2:35:40
        *  @param className
        *  @return
        *  @throws InstantiationException
        *  @throws IllegalAccessException
        *  @throws ClassNotFoundException  Class<?>
         */
        @SuppressWarnings("unchecked")
        public  static Class<? extends Job>stringToClazz(String className) {
                try {
                        return (Class<? extends Job>) Class.forName(className).newInstance().getClass();
                } catch (InstantiationException e) {
                        e.printStackTrace();
                } catch (IllegalAccessException e) {
                        e.printStackTrace();
                } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                }
               throw new RuntimeException("传入类错误");
        }

}
