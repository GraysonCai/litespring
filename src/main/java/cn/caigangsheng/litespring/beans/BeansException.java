package cn.caigangsheng.litespring.beans;

/**
 * @author cgs
 * @Date 2018-08-07 23:07
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
