package cn.caigangsheng.litespring.core;

import cn.caigangsheng.litespring.beans.factory.support.DefaultBeanFactory;
import cn.caigangsheng.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import cn.caigangsheng.litespring.context.ApplicationContext;

/**
 * @author cgs
 * @Date 2018-08-09 0:14
 */
public class ClassPathXMLApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory;

    public ClassPathXMLApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        reader.loadBeanDefinition(configFile);
    }

    @Override
    public Object getBean(String beanId) {
        return factory.getBean(beanId);
    }
}
