package gc.jit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by innopolis on 10.01.2017.
 */
public class ThreadProfile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("waiting");
        scanner.next();
        for(int i =0; i < 10000; i++) {
            final int j = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(j + "I start");

                    try {
                        Random random = new Random();
                        Thread.sleep(random.nextInt(10000));
                        List<String> stringList = new ArrayList<>();
                        for(int i = 0; i < random.nextInt(10000);i++) {
                            stringList.add(i + " I of Thread " + j);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(j + "I end");
                }
            });
            thread.setName("Thread " + j);
            thread.start();
        }
        scanner.next();
    }
}
