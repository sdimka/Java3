package J3Lesson1;

import java.util.Random;


public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("Bla-bla!");
        int m;
        for (int i = 0; i < 5; i++){
            m = random.nextInt(100);
            System.out.println(m);
        }
    }
}
