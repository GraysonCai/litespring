package cn.caigangsheng.litespring.beans.factory;

import cn.caigangsheng.litespring.beans.BeansException;

/**
 * @author cgs
 * @Date 2018-08-07 23:10
 */
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String message) {
        super(message);
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
