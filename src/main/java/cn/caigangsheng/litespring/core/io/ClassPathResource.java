package cn.caigangsheng.litespring.core.io;

import cn.caigangsheng.litespring.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author cgs
 * @Date 2018-08-18 20:09
 */
public class ClassPathResource implements Resource {

    private ClassLoader classLoader;
    private String configFile;

    public ClassPathResource(String configFile) {
        this(configFile, (ClassLoader) null);
    }

    public ClassPathResource(String configFile, ClassLoader classLoader) {
        this.classLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
        this.configFile = configFile;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = null;
        is = this.classLoader.getResourceAsStream(this.configFile);
        if (is == null) {
            throw new FileNotFoundException(this.configFile + " cannot be opened");
        }
        return is;
    }

    @Override
    public String getDescription() {
        return this.configFile;
    }
}
