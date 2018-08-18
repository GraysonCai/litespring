package cn.caigangsheng.litespring.beans.factory.support;

import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.config.SingletonBeanRegister;
import cn.caigangsheng.litespring.core.io.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cgs
 * @Date 2018-08-18 23:41
 */
public class DefaultSingletonBeanRegister implements SingletonBeanRegister {

    private Map<String, Object> singletonMap = new ConcurrentHashMap<>(64);

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        Assert.notNull(beanName, "bean name must be not null");

        Object singletonBean = this.singletonMap.get(beanName);
        if(singletonBean != null) {
            throw new BeanDefinitionStoreException("");
        }
        this.singletonMap.put(beanName, singletonObject);
    }

    @Override
    public Object getSingleton(String beanName) {
        return this.singletonMap.get(beanName);
    }
}
