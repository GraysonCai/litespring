package cn.caigangsheng.litespring.beans.factory.config;

import cn.caigangsheng.litespring.beans.factory.BeanFactory;

/**
 * @author cgs
 * @Date 2018-08-18 21:47
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    ClassLoader getBeanClassLoader();
    void setBeanClassLoader(ClassLoader beanClassLoader);
}
