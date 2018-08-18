package cn.caigangsheng.test.v1;

import cn.caigangsheng.litespring.beans.BeanDefinition;
import cn.caigangsheng.litespring.beans.factory.BeanCreationException;
import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.support.DefaultBeanFactory;
import cn.caigangsheng.litespring.beans.factory.xml.XMLBeanDefinitionReader;
import cn.caigangsheng.litespring.core.io.ClassPathResource;
import cn.caigangsheng.litespring.core.io.Resource;
import cn.caigangsheng.litespring.service.v1.PetStoreService;
import cn.caigangsheng.litespring.service.v1.PrototypeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinition(resource);
        BeanDefinition beanDefinition = reader.getBeanDefinition("petStoreService");

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());

        assertEquals("cn.caigangsheng.litespring.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) beanFactory.getBean("petStoreService");
        assertNotNull( petStoreService );

        PetStoreService petStoreService1 = (PetStoreService) beanFactory.getBean("petStoreService");
        assertTrue(petStoreService.equals(petStoreService1));

        PrototypeService prototypeService = (PrototypeService) beanFactory.getBean("prototypeService");
        assertNotNull( petStoreService );

        PrototypeService prototypeService1 = (PrototypeService) beanFactory.getBean("prototypeService");
        assertFalse(prototypeService.equals(prototypeService1));
    }

    @Test
    public void testInvalidBean() {
        Resource resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinition(resource);
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
            Resource resource = new ClassPathResource("petstore-v1xx.xml");
            reader.loadBeanDefinition(resource);
        } catch (BeanDefinitionStoreException e) {
            return;
        }
        Assert.fail("expect BeanDefinitionStoreException");
    }
}
