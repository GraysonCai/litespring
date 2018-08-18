package cn.caigangsheng.litespring.beans.factory.config;

/**
 * @author cgs
 * @Date 2018-08-18 23:39
 */
public interface SingletonBeanRegister {

    void registerSingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);

}
