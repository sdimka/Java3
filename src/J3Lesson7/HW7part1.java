package J3Lesson7;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

public class HW7part1 {
    static String ClazzToExplName = "J3Lesson7.exploredClass";
    static Pattern p = Pattern.compile("[0-9]\\."); //\w+\.

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("No arguments, uses defaults ");
        } else ClazzToExplName = args[0];

        int lines = 0;
        try {
            Class<?> c = Class.forName(ClazzToExplName);
            Method[] methods = c.getMethods();
            Constructor[] constrs = c.getConstructors();

            for (Method m : methods)
                System.out.println(p.matcher(m.toString()).replaceAll(""));
//                System.out.println(m.toString());
            for (Constructor con : constrs)
//                System.out.println(p.matcher(con.toString()).replaceAll(""));
                System.out.println(con.toString());
            lines = methods.length + constrs.length;

        } catch (ClassNotFoundException е) {
            System.out.println("No such class: " + е);
        }

    }
}
