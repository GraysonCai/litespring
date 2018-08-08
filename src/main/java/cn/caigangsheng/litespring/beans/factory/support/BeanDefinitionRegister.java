package cn.caigangsheng.litespring.beans.factory.support;

import cn.caigangsheng.litespring.beans.BeanDefinition;

/**
 * @author cgs
 * @Date 2018-08-08 23:53
 */
public interface BeanDefinitionRegister {

    BeanDefinition getBeanDefinition(String beanId);

    void registerBeanDefinition(String beanId, BeanDefinition beanDefinition);
}
