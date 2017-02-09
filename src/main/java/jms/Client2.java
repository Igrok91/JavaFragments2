package jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by innopolis on 23.01.2017.
 */
public class Client2 {
    private ConnectionFactory factory = null;
    private Connection connection = null;
    private Session session = null;
    private Destination destination = null;
    private MessageConsumer consumer = null;
    private MessageProducer producer = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Client2() {
        factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        try {
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue("chat");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Client2 c = new Client2();
        c.message();


    }

    private void message() throws IOException {

            new Thread(new Receiver()).start();

            new Thread(new Sender()).start();

    }


    private  class Receiver  implements Runnable {


        @Override
        public void run() {
            try {
                consumer = session.createConsumer(destination);
                while(true) {
                    Message message = consumer.receive();
                    if (message instanceof TextMessage) {
                        TextMessage text = (TextMessage) message;
                        System.out.println("Message: " + text.getText());
                    }
                }



            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    private class Sender implements Runnable {



        @Override
        public void run() {
            try {
                producer = session.createProducer(destination);
                TextMessage message = session.createTextMessage();
                while (true) {
                    message.setText(reader.readLine());
                    producer.send(message);
                    System.out.println("Sent: " + message.getText());
                }


            } catch (JMSException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
