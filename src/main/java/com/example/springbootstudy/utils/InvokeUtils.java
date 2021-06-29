package com.example.springbootstudy.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InvokeUtils {



    public static Object invokeMethod(Object object, String methodName, Object... parameters) {
        //根据 对象、方法名和对应的方法参数 通过反射 调用上面的方法获取 Method 对象
        Method method = getDeclaredMethod(object, methodName, parameters != null ? parameters.length : 0);
        //抑制Java对方法进行检查,主要是针对私有方法而言
        method.setAccessible(true);
        try {
            if (null != method) {

                //调用object 的 method 所代表的方法，其方法的参数是 parameters
                return method.invoke(object, parameters);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public static Method getDeclaredMethod(Object object, String methodName, int length) {
        Method method = null;

        for (Class<?> clazz = object.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Method[] methods = clazz.getDeclaredMethods();
                for (Method m : methods) {
                    if (m.getName().equals(methodName) && (m.getParameterTypes() != null ? m.getParameterTypes().length : 0) == length) {
                        System.out.println("methodName :" + methodName + ",paramter.size = " + length);
                        return m;
                    }
                }
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }

        return null;
    }


    public static Method getMethod(Class clazz, String methodName) {
        Method methods[] = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        return null;
    }

    public static <T> T getFieldValue(Object target, String name) {
        Field field = getField(target.getClass(), name);
        if (field != null) {
            try {
                field.setAccessible(true);
                return (T) field.get(target);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Field getField(Class clazz, String name) {
        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (name.equals(field.getName())) {
                return field;
            }
        }
        return null;
    }
}
