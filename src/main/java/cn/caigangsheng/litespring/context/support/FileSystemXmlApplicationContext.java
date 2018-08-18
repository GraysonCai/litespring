package cn.caigangsheng.litespring.context.support;

import cn.caigangsheng.litespring.core.io.FileSystemResource;
import cn.caigangsheng.litespring.core.io.Resource;

/**
 * @author cgs
 * @Date 2018-08-18 20:46
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {

    public FileSystemXmlApplicationContext(String path) {
        super(path);
    }

    @Override
    public Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
