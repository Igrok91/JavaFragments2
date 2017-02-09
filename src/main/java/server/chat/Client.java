package server.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;

/**
 * Created by innopolis on 21.12.2016.
 */
public class Client {
    private static Logger log = LoggerFactory.getLogger(Client.class);

    BufferedReader in;
    private void run() throws IOException {

        // Make connection and initialize streams

        Socket socket = new Socket("localhost", 8678);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));

        // Process all messages from server, according to the protocol.

        while (true) {
            String line = in.readLine();
            if (line.startsWith("SUBMIT")) {
                new MessageThread(socket).start();
            }
             else if (line.startsWith("NAMEACCEPTED")) {
                System.out.println("Success");;
            } else if (line.startsWith("MESSAGE")) {
                System.out.println(line);
            }
        }
    }
    private static class MessageThread extends Thread {
        private Socket socket;
        private PrintWriter out;
        public MessageThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            System.out.println("Введите имя");

            try {
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
            while (true) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String message = null;
                try {
                    message = reader.readLine();
                    out.println(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }



    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        client.run();
    }


}
