package J3Lesson4;

import java.io.*;

public class WaitNotifyClass {
    private final Object monitor = new Object();
    private volatile char currentLetter = 'A';

    private volatile File file = new File ("fileHW4.txt");

    public static void main(String[] args) {
        WaitNotifyClass w = new WaitNotifyClass();
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
            w.writeToFile("f1");
        });
        Thread f2 = new Thread(() -> {
            w.writeToFile("f2");
        });
        Thread f3 = new Thread(() -> {
            w.writeToFile("f3");
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

    void writeToFile(String name){
        synchronized (file) {
            try {
                FileWriter fw = new FileWriter(file);
                for (int i = 0; i < 10; i++) {
                    try {
                        fw.write("Bla-" + name);
                        fw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}