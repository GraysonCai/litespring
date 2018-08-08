package cn.caigangsheng.litespring.beans.factory;

import cn.caigangsheng.litespring.beans.BeansException;

/**
 * @author cgs
 * @Date 2018-08-07 23:11
 */
public class BeanCreationException extends BeansException {

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
