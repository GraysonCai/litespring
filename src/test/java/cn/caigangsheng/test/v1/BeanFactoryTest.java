package cn.caigangsheng.test.v1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import cn.caigangsheng.litespring.beans.BeanDefinition;
import cn.caigangsheng.litespring.beans.factory.BeanCreationException;
import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.BeanFactory;
import cn.caigangsheng.litespring.beans.factory.support.DefaultBeanFactory;
import cn.caigangsheng.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import cn.caigangsheng.litespring.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class BeanFactoryTest
{

    private DefaultBeanFactory beanFactory = null;

    private XMLBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        beanFactory = new DefaultBeanFactory();
        reader = new XMLBeanDefinitionReader(beanFactory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinition("petstore-v1.xml");

        BeanDefinition beanDefinition = reader.getBeanDefinition("petStoreService");

        assertEquals("cn.caigangsheng.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStoreService");

        assertNotNull( petStoreService );
    }

    @Test
    public void testInvalidBean() {
        reader.loadBeanDefinition("petstore-v1.xml");
        BeanDefinition beanDefinition = reader.getBeanDefinition("petStoreService");
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
            reader.loadBeanDefinition("petstore-v1xx.xml");
            BeanDefinition beanDefinition = reader.getBeanDefinition("petStoreService");
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
