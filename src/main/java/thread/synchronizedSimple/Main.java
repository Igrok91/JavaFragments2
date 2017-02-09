package thread.synchronizedSimple;

/**
 * Created by innopolis on 09.12.2016.
 * Напишите программу, которая каждую секунду отображает на экране данные о времени,
 *  прошедшем от начала сессии, а другой ее поток выводит сообщение каждые 5 секунд.
 *  Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение, потоком,
 *  отсчитывающим время.
 */
public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadMessage threadMessage = new ThreadMessage(service, "Success");
        ThreadMessage2 threadMessage2 = new ThreadMessage2(service, "Go");
        ThreadCount threadCount = new ThreadCount(service);
        threadMessage.start();
        threadCount.start();
        //threadMessage2.start();


    }
}
