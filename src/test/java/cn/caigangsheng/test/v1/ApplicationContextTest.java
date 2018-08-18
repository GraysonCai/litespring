package cn.caigangsheng.test.v1;

import cn.caigangsheng.litespring.context.ApplicationContext;
import cn.caigangsheng.litespring.context.support.ClassPathXmlApplicationContext;
import cn.caigangsheng.litespring.context.support.FileSystemXmlApplicationContext;
import cn.caigangsheng.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author cgs
 * @Date 2018-08-09 0:07
 */
public class ApplicationContextTest {

    @Test
    public void testGetBeanByClassPathXml() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStoreService");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testGetBeanByFileSystemXml() {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("D:\\Users\\cgs\\GitRepository\\litespring\\src\\test\\resources\\petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) ctx.getBean("petStoreService");
        Assert.assertNotNull(petStoreService);
    }
}
