package cn.caigangsheng.litespring.core.io;

/**
 * @author cgs
 * @Date 2018-08-18 20:19
 */
public abstract class Assert {
    public static void notNull(Object object, String message) {
        if(object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
