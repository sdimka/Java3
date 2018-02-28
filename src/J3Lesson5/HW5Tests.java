package J3Lesson5;

import java.util.*;
import java.util.concurrent.*;

class Ippo {
    private Random rand = new Random();

    private int distance = rand.nextInt(250);
    private CountDownLatch start;
    private CountDownLatch finish;

    private List<String> horses = new ArrayList<String>();

    public Ippo(String... names) {
        this.horses.addAll(Arrays.asList(names));
    }

    public int getDistance() {
        return distance;
    }

    public void run() throws InterruptedException {

        System.out.println("And the horses are stepping up to the gate...");
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch finish = new CountDownLatch(horses.size());
        final List<String> places = Collections.synchronizedList(new ArrayList<String>());

        for (final String h : horses) {
            new Thread(new Runnable() {
                public void run() {
                    try
                    {
                        System.out.println(h +
                                " stepping up to the gate...");
                        start.await();

                        int traveled = 0;
                        while (traveled < distance)
                        {
                            // через 0-2 секунды....
                            Thread.sleep(rand.nextInt(3) * 1000);

                            // ... лошадь проходит дистанцию 0-14 пунктов
                            traveled += rand.nextInt(15);
                            System.out.println(h +
                                    " advanced to " + traveled + "!");
                        }
                        finish.countDown();
                        System.out.println(h +
                                " crossed the finish!");
                        places.add(h);
                    }
                    catch (InterruptedException intEx)
                    {
                        System.out.println("ABORTING RACE!!!");
                        intEx.printStackTrace();
                    }
                }
            }).start();
        }

        System.out.println("And... they're off!");
        start.countDown();

        finish.await();
        System.out.println("And we have our winners!");
        System.out.println(places.get(0) + " took the gold...");
        System.out.println(places.get(1) + " got the silver...");
        System.out.println("and " + places.get(2) + " took home the bronze.");
    }
}

public class HW5Tests
{
    public static void main(String[] args)
            throws InterruptedException, java.io.IOException
    {
        System.out.println("Prepping...");

        Ippo r = new Ippo(
                "Beverly Takes a Bath",
                "RockerHorse",
                "Phineas",
                "Ferb",
                "Tin Cup",
                "I'm Faster Than a Monkey",
                "Glue Factory Reject"
        );

        System.out.println("It's a race of " + r.getDistance() + " lengths");

        System.out.println("Press Enter to run the race....");
        System.in.read();

        r.run();
    }
}