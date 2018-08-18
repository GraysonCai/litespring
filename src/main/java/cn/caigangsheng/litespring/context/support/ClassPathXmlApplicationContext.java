package cn.caigangsheng.litespring.context.support;

import cn.caigangsheng.litespring.core.io.ClassPathResource;
import cn.caigangsheng.litespring.core.io.Resource;

/**
 * @author cgs
 * @Date 2018-08-09 0:14
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new ClassPathResource(path, this.getBeanClassLoader());
    }
}
