package J3Lesson7;

/**
 * Java. Level 3. Lesson 7
 * Task 1
 *
 * @author Dmitriy Semenov
 * @version 0.1 dated Mar 16, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class HW7part1 {
    static String ClazzToExplName = "J3Lesson7.exploredClass";
    static Pattern p = Pattern.compile("\\w+\\."); //\w+\.

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No arguments, uses defaults ");
        } else ClazzToExplName = args[0];

        try {
            Class<?> c = Class.forName(ClazzToExplName);
            Method[] methods = c.getMethods();
            Constructor[] constrs = c.getConstructors();
            Field[] fields = c.getDeclaredFields();
            Class<?>[] interfaces = c.getInterfaces();

            System.out.println(" ========== Class name ==================");
            System.out.println(c.getName());
            System.out.println(" ---------- Extends from ----------------");
            System.out.println(c.getGenericSuperclass());
            System.out.println(" ---------- Implements ------------------");
            for (Class<?> intf : interfaces) System.out.println(intf.getName());

            int prC = 0;

            System.out.println(" ========== Class fields ================");
            for (Field fil : fields)
                System.out.println(p.matcher(fil.toString()).replaceAll(""));

            System.out.println(" ========== Constructors ================");
            for (Constructor con : constrs)
                System.out.println(p.matcher(con.toString()).replaceAll(""));

            System.out.println(" ========== Own methods =================");
            for (Method m : methods) {
                if (!m.getDeclaringClass().equals(c) && prC == 0){
                    System.out.println(" ========== Inherited methods ============");
                    prC++;
                }
                System.out.println(p.matcher(m.toString()).replaceAll(""));
            }



        } catch (ClassNotFoundException е) {
            System.out.println("No such class: " + е);
        }

    }
}
