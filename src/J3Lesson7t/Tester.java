package J3Lesson7t;

import java.lang.annotation.*;
import java.lang.reflect.Method;

public class Tester {

    static Class<?> clazz = MyTest.class;

    public static void main(String[] args) {
//        Method[] methods = clazz.getMethods();
        Object mt = new MyTest();
        String methodName = "test1";
        try {
            Method method = mt.getClass().getMethod(methodName);
            method.invoke(mt);
        } catch (Exception e) {
            e.printStackTrace();
        }



//        for (Method m : methods) {
//            Annotation[] annotation = m.getAnnotations();
//            for (Annotation a: annotation){
//                System.out.println(a.toString());
//            }
//            start(mt, m);
//            System.out.println(m.toString());
//        }

    }

    static void start(Class<?> cls, Method m){
//        Class<?> cl = new cls;
//        Method mth = cls.getMethod(m);
//            m.invoke(cls);

    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

@interface Test {
    int priority();
}
