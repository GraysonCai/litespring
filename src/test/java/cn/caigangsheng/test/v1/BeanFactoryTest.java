package cn.caigangsheng.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cn.caigangsheng.litespring.beans.BeanDefinition;
import cn.caigangsheng.litespring.beans.factory.BeanCreationException;
import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.BeanFactory;
import cn.caigangsheng.litespring.beans.factory.support.DefaultBeanFactory;
import cn.caigangsheng.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BeanFactoryTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testGetBean() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("petStoreService");

        assertEquals("cn.caigangsheng.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStoreService");

        assertNotNull( petStoreService );
    }

    @Test
    public void testInvalidBean() {
        BeanFactory beanFactory = new DefaultBeanFactory("petstore-v1.xml");
        try {
            beanFactory.getBean("invalidBean");
        } catch (BeanCreationException e) {
            return;
        }
        Assert.fail("expect BeanCreationException");
    }

    @Test
    public void testNotExistConfigFile() {
        try {
            BeanFactory beanFactory = new DefaultBeanFactory("petstore-v2.xml");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
