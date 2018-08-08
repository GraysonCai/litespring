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
public class DefaultBeanFactory implements BeanFactory {

    public static final String ATTRIBUTE_ID = "id";

    public static final String ATTRIBUTE_CLASS = "class";

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public DefaultBeanFactory(String configFile) {
        InputStream is = null;
        try {
            ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
            is = defaultClassLoader.getResourceAsStream(configFile);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(is);

            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String beanId = element.attributeValue(ATTRIBUTE_ID);
                String beanClassName = element.attributeValue(ATTRIBUTE_CLASS);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanId, beanClassName);
                beanDefinitionMap.put(beanId, beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("parse bean definition config file fail.", e);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        if(beanDefinitionMap == null || beanDefinitionMap.isEmpty())
            return null;
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
