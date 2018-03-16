package J3Lesson7t;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class Tester {

    public static void main(String[] args) {
        Class<?> clazz = MyTest.class;
        Tester.start(clazz);
    }

    static void start(Class<?> clz){
        Object mt = null;
        Method bs = null;
        Method as = null;

        Method[] methods = clz.getMethods();
        List<TestElement> testElements = new ArrayList<>();

        for (Method m : methods){
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a: annotations){
                if (a.annotationType().equals(BeforeSuite.class)){
                    if (bs != null) throw new RuntimeException("Duplicated BeforeSuite");
                        else bs = m;
                }
                if (a.annotationType().equals(AfterSuite.class)){
                    if (as != null) throw new RuntimeException("Duplicated AfterSuite");
                    else as = m;
                }
                if (a.annotationType().equals(Test.class)){
                    Test test = m.getAnnotation(Test.class);
                    testElements.add(new TestElement(test.priority(), m));
                }
            }
        }

        try {
            mt = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        Collections.sort(testElements);

        try {

            if (bs != null) bs.invoke(mt);

            for (TestElement el : testElements){
                el.method.invoke(mt);
            }

            if (as != null) as.invoke(mt);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    static class TestElement implements Comparable {
        int prior;
        Method method;

        public TestElement(int prior, Method method) {
            this.prior = prior;
            this.method = method;
        }

        @Override
        public int compareTo(Object o) {
            int comparePrior = ((TestElement)o).prior;
            return this.prior - comparePrior;
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
    int priority() default -1;
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface BeforeSuite {

}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface AfterSuite {

}
