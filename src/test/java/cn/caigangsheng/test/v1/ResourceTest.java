package cn.caigangsheng.test.v1;

import cn.caigangsheng.litespring.core.io.ClassPathResource;
import cn.caigangsheng.litespring.core.io.FileSystemResource;
import cn.caigangsheng.litespring.core.io.Resource;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cgs
 * @Date 2018-08-18 19:59
 */
public class ResourceTest {

    private Resource resource;

    @Test
    public void testClassPathResource() throws IOException {
        resource = new ClassPathResource("petstore-v1.xml");
    }

    @Test
    public void testFileSystemResource() {
        resource = new FileSystemResource("D:\\Users\\cgs\\GitRepository\\litespring\\src\\test\\resources\\petstore-v1.xml");
    }

    @After
    public void assertStreamNotNull() throws IOException {
        InputStream is = null;
        try {
            is = resource.getInputStream();
            Assert.assertNotNull(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

}
