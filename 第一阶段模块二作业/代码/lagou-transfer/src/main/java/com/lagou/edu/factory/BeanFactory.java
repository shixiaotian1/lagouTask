package com.lagou.edu.factory;

import com.alibaba.druid.util.StringUtils;
import com.lagou.edu.annotation.Autowired;
import com.lagou.edu.annotation.Service;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.reflections.Reflections;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author 应癫
 *
 * 工厂类，生产对象（使用反射技术）
 */
public class BeanFactory {

    /**
     * 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
     * 任务二：对外提供获取实例对象的接口（根据id获取）
     */

    private static Map<String,Object> map = new HashMap<>();  // 存储对象

    /**
     * 加载beans.xml的静态方法
     */
    static {
        // 任务一：读取解析xml，通过反射技术实例化对象并且存储待用（map集合）
        // 加载xml
        InputStream resourceAsStream = BeanFactory.class.getClassLoader().getResourceAsStream("beans.xml");
        // 解析xml
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(resourceAsStream);
            Element rootElement = document.getRootElement();
            // 常规解析baen方式
            normalAnalysis(rootElement);
            // 解析需要扫描的包
            List<Element> beanList = rootElement.selectNodes("//component-scan");
            for (Element element : beanList) {
                // 取到需要扫描的包名
                String aPackage = element.attributeValue("package");
                Reflections f = new Reflections(aPackage);
                // 从包中扫描出包含Service注解的类
                Set<Class<?>> set = f.getTypesAnnotatedWith(Service.class);
                // 将类进行属性注入，并封装到Map中
                classHandle(set);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理类
     * @param set
     */
    private static void classHandle(Set<Class<?>> set) throws Exception{
        // 遍历注解类
        for (Class<?> aClass : set) {
            // 获取注解，并根据注解value值，存着map中备用
            Service annotation = aClass.getAnnotation(Service.class);
            String beanKey = splicingKey(aClass, annotation.value());
            map.put(beanKey, aClass.newInstance());
        }
        // 遍历注解类，开始属性注入
        for (Class<?> aClass : set) {
            Service annotation = aClass.getAnnotation(Service.class);
            String beanKey = splicingKey(aClass, annotation.value());
            Object parentObject = map.get(beanKey);
            // 获取类中的属性
            Field[] fields = aClass.getDeclaredFields();
            for (Field field : fields) {
                // 开启暴力访问
                field.setAccessible(true);
                // 找到被Autowired注解的属性
                Autowired annotation1 = field.getAnnotation(Autowired.class);
                field.getType();
                // 开始注入
                if(annotation1 != null){
                    // 遍历父对象中的所有方法，找到"set" + name
                    Method[] methods = aClass.getMethods();
                    Object diObject = map.get(field.getName());
                    for (int j = 0; j < methods.length; j++) {
                        Method method = methods[j];
                        String setMonthod = getMethodNameUpperCase(field.getName());
                        if(method.getName().equalsIgnoreCase("set" + setMonthod)) {  // 该方法就是 setAccountDao(AccountDao accountDao)
                            method.invoke(parentObject, diObject);
                        }
                    }
                }
            }
            map.put(beanKey, parentObject);
        }
    }

    // 任务二：对外提供获取实例对象的接口（根据id获取）
    public static  Object getBean(String id) {
        return map.get(id);
    }

    public static void normalAnalysis(Element rootElement) throws  Exception{
        List<Element> beanList = rootElement.selectNodes("//bean");
        for (int i = 0; i < beanList.size(); i++) {
            Element element =  beanList.get(i);
            // 处理每个bean元素，获取到该元素的id 和 class 属性
            String id = element.attributeValue("id");        // accountDao
            String clazz = element.attributeValue("class");  // com.lagou.edu.dao.impl.JdbcAccountDaoImpl
            // 通过反射技术实例化对象
            Class<?> aClass = Class.forName(clazz);
            Object o = aClass.newInstance();  // 实例化之后的对象
            // 存储到map中待用
            map.put(id,o);
        }
        // 实例化完成之后维护对象的依赖关系，检查哪些对象需要传值进入，根据它的配置，我们传入相应的值
        // 有property子元素的bean就有传值需求
        List<Element> propertyList = rootElement.selectNodes("//property");
        // 解析property，获取父元素
        for (int i = 0; i < propertyList.size(); i++) {
            Element element =  propertyList.get(i);   //<property name="AccountDao" ref="accountDao"></property>
            String name = element.attributeValue("name");
            String ref = element.attributeValue("ref");
            // 找到当前需要被处理依赖关系的bean
            Element parent = element.getParent();
            // 调用父元素对象的反射功能
            String parentId = parent.attributeValue("id");
            Object parentObject = map.get(parentId);
            // 遍历父对象中的所有方法，找到"set" + name
            Method[] methods = parentObject.getClass().getMethods();
            for (int j = 0; j < methods.length; j++) {
                Method method = methods[j];
                if(method.getName().equalsIgnoreCase("set" + name)) {  // 该方法就是 setAccountDao(AccountDao accountDao)
                    method.invoke(parentObject,map.get(ref));
                }
            }
            // 把处理之后的parentObject重新放到map中
            map.put(parentId,parentObject);
        }
    }

    /**
     * 根据注解创建出key
     * @param aClass
     * @param value
     * @return
     */
    public static String splicingKey(Class<?> aClass, String value) throws Exception {
        if(StringUtils.isEmpty(value)){
            return getMethodNameTransLowerCase(aClass.getSimpleName());
        }
        return value;
    }

    // 把一个字符串的第一个字母大写
    private static String getMethodNameUpperCase(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    // 把一个字符串的第一个字母小写
    private static String getMethodNameTransLowerCase(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'A' + 'a');
        return new String(items);
    }
}
