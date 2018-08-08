package cn.caigangsheng.litespring.beans.factory.support;

import cn.caigangsheng.litespring.beans.BeanDefinition;
import cn.caigangsheng.litespring.beans.factory.BeanCreationException;
import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.BeanFactory;
import cn.caigangsheng.litespring.utils.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author cgs
 * @Date 2018-08-07 22:11
 */
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegister {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory() {

    }

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanId, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanDefinitionMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanId);
        if(beanDefinition == null) {
            throw new BeanCreationException("'" + beanId + "' Bean Definition does not exist");
        }

        String beanClassName = beanDefinition.getBeanClassName();
        ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
        try {
            Class<?> beanClass = classLoader.loadClass(beanClassName);
            return beanClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + "fail.", e);
        }
    }
}
