package cn.caigangsheng.test.v1;

import cn.caigangsheng.litespring.context.ApplicationContext;
import cn.caigangsheng.litespring.core.ClassPathXMLApplicationContext;
import cn.caigangsheng.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author cgs
 * @Date 2018-08-09 0:07
 */
public class ApplicationContextTest {

    @Test
    public void testGetBean() {
        ApplicationContext ctx = new ClassPathXMLApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStoreService");
        Assert.assertNotNull(petStoreService);
    }
}
