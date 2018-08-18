package cn.caigangsheng.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cgs
 * @Date 2018-08-18 20:08
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
    String getDescription();
}
