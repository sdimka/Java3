package J3Lesson4;

/**
 * Java. Level 3. Lesson 4
 * Task 1&2
 *
 * @author Dmitriy Semenov
 * @version 0.1 dated Feb 23, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.io.*;

public class HW4 {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';
    private volatile String trName = "f1";

    private volatile File file = new File ("fileHW4.txt");

    public static void main(String[] args) {

        HW4 w = new HW4();
        Thread t1 = new Thread(() -> {
            w.printCH('A', 'B');
        });
        Thread t2 = new Thread(() -> {
            w.printCH('B', 'C');
        });
        Thread t3 = new Thread(() -> {
            w.printCH('C', 'A');
        });
        t1.start();
        t2.start();
        t3.start();

        try {
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread f1 = new Thread(() -> {
            w.writeToFile("f1", "f2");
        });
        Thread f2 = new Thread(() -> {
            w.writeToFile("f2", "f3");
        });
        Thread f3 = new Thread(() -> {
            w.writeToFile("f3", "f1");
        });

        f1.start();
        f2.start();
        f3.start();
    }

    void printCH(char current, char next) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (currentLetter != current) monitor.wait();
                    System.out.print(current);
                    currentLetter = next;
                    monitor.notifyAll();
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    void writeToFile(String name, String next){
        synchronized (file) {
            try {
                FileWriter fw = new FileWriter(file, true);
                for (int i = 0; i < 3; i++) {
                    while (!trName.equals(name)) file.wait();
                    try {
                        fw.write(Thread.currentThread().getName() + " " + i + " " + name + "\n");
                        fw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    trName = next;
                    file.notifyAll();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}