package cn.caigangsheng.litespring.beans.factory.xml;

import cn.caigangsheng.litespring.beans.BeanDefinition;
import cn.caigangsheng.litespring.beans.factory.BeanDefinitionStoreException;
import cn.caigangsheng.litespring.beans.factory.BeanFactory;
import cn.caigangsheng.litespring.beans.factory.support.BeanDefinitionRegister;
import cn.caigangsheng.litespring.beans.factory.support.GenericBeanDefinition;
import cn.caigangsheng.litespring.utils.ClassUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;


/**
 * @author cgs
 * @Date 2018-08-08 23:21
 */
public class XMLBeanDefinitionReader {

    public static final String ATTRIBUTE_ID = "id";

    public static final String ATTRIBUTE_CLASS = "class";

    private BeanDefinitionRegister register;

    public XMLBeanDefinitionReader(BeanDefinitionRegister register) {
        this.register = register;
    }

    public void loadBeanDefinition(String configFile) {
        InputStream is = null;
        try {
            ClassLoader defaultClassLoader = ClassUtils.getDefaultClassLoader();
            is = defaultClassLoader.getResourceAsStream(configFile);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(is);

            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String beanId = element.attributeValue(ATTRIBUTE_ID);
                String beanClassName = element.attributeValue(ATTRIBUTE_CLASS);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanId, beanClassName);
                register.registerBeanDefinition(beanId, beanDefinition);
            }
        } catch (DocumentException e) {
            throw new BeanDefinitionStoreException("parse bean definition config file fail.", e);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public BeanDefinition getBeanDefinition(String beanId) {
       return register.getBeanDefinition(beanId);
    }
}
