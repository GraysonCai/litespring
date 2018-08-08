package cn.caigangsheng.litespring.beans.factory;

import cn.caigangsheng.litespring.beans.BeanDefinition;

/**
 * @author cgs
 * @Date 2018-08-07 22:10
 */
public interface BeanFactory {

    Object getBean(String beanId);
}
