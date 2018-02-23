package J3Lesson4;

/**
 * Java. Level 3. Lesson 4
 * Task 3 - MFP realisation :)
 *
 * @author Dmitriy Semenov
 * @version 0.1 dated Feb 23, 2018
 * @link https://github.com/sdimka/Java3
 *
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class HW4MFP {
    private volatile String prntLock = "P";
    private volatile String scanLock = "S";
    public static void main(String[] args) {
    new HW4MFP();

    }

    public HW4MFP (){
        Deque<task> queue = new LinkedList<>();
        queue.add(new task('p', 5));
        queue.add(new task('p', 8));
        queue.add(new task('s', 5));
        queue.add(new task('s', 10));
        queue.add(new task('p', 8));

        Thread trd[] = new Thread[queue.size()];

        while (queue.size() > 0){
            if (queue.getFirst().getType() == 'p'){
                    int pg = queue.pop().getPages();
                    trd[queue.size()] = new Thread(() -> print(pg));
                    trd[queue.size()].start();
                } else if (queue.getFirst().getType() == 's'){
                    int pg = queue.pop().getPages();
                    trd[queue.size()] = new Thread(() -> scan(pg));
                    trd[queue.size()].start();
                }else {
                        System.out.println(queue.pop().getType() + "Unknown type of task");
            }
        }
    }

    void print(int pages){
     synchronized (prntLock){
        for (int i = 0; i < pages; i++){
            System.out.println("Printing page #" + i + " in queue " + Thread.currentThread().getName());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     }
    }
    void scan(int pages){
        synchronized (scanLock){
            for (int i = 0; i < pages; i++){
                System.out.println("Scanning page #" + i + " in queue " + Thread.currentThread().getName());
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class task {
        private char type;
        private int pages;

        public task(char type, int pages) {
            this.type = type;
            this.pages = pages;
        }

        public char getType() {
            return type;
        }

        public int getPages() {
            return pages;
        }
    }
}
