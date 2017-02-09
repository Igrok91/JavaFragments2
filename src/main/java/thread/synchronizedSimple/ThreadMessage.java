package thread.synchronizedSimple;

/**
 * Created by innopolis on 09.12.2016.
 * Напишите программу, которая каждую секунду отображает на экране данные о времени,
 *  прошедшем от начала сессии, а другой ее поток выводит сообщение каждые 5 секунд.
 *  Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение, потоком,
 *  отсчитывающим время.
 */
public class ThreadMessage extends Thread {
    Service service;
    String message;
    public ThreadMessage(Service s, String str) {
        this.service =s;
        this.message =str;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (service) {
                int n;
                n = service.last();
                System.out.println(n);
                if (n % 5 == 0) {
                    System.out.println(message);
                }
                if (n % 7 == 0) {
                  new  ThreadMessage2(service, "Go").start();
                }
            }
        }
    }
}
