package cn.caigangsheng.litespring.context.support;

import cn.caigangsheng.litespring.beans.factory.config.ConfigurableBeanFactory;
import cn.caigangsheng.litespring.beans.factory.support.DefaultBeanFactory;
import cn.caigangsheng.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import cn.caigangsheng.litespring.context.ApplicationContext;
import cn.caigangsheng.litespring.core.io.Resource;
import cn.caigangsheng.litespring.utils.ClassUtils;

/**
 * @author cgs
 * @Date 2018-08-18 20:55
 */
public abstract class AbstractApplicationContext implements ApplicationContext,ConfigurableBeanFactory {

    private DefaultBeanFactory factory;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String path) {
        factory = new DefaultBeanFactory();
        XMLBeanDefinitionReader reader = new XMLBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(path);
        reader.loadBeanDefinition(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    public abstract Resource getResourceByPath(String path);

    @Override
    public Object getBean(String beanId) {
        return this.factory.getBean(beanId);
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return (this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }
}
