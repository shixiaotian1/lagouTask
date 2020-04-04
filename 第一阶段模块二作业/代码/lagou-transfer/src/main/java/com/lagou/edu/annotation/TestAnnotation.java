package com.lagou.edu.annotation;

import com.alibaba.druid.util.StringUtils;
import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.utils.ConnectionUtils;
import org.reflections.Reflections;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

public class TestAnnotation {

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        Reflections f = new Reflections("com.lagou.edu");
        //入参 目标注解类
        Set<Class<?>> set = f.getTypesAnnotatedWith(Service.class);
        // 遍历注解类
        for (Class<?> aClass : set) {
            // 获取注解，并根据注解value值，存着map中备用
            Service annotation = aClass.getAnnotation(Service.class);
            String beanKey = splicingKey(aClass, annotation.value());
            map.put(beanKey, aClass.newInstance());
        }
        System.out.println(map);
        // 遍历注解类，开始属性注入
        for (Class<?> aClass : set) {
            // 获取注解，并根据注解value值，存着map中备用
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
        System.out.println(map);
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
