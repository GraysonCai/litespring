package cn.caigangsheng.litespring.utils;

/**
 * @author cgs
 * @Date 2018-08-07 22:37
 */
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader classLoader = null;
        classLoader = Thread.currentThread().getContextClassLoader();
        if(classLoader == null) {
            classLoader = ClassUtils.class.getClassLoader();
            if(classLoader == null) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }
}
