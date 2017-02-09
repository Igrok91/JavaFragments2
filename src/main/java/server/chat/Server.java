package server.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by innopolis on 21.12.2016.
 */
public class Server {
    public static final int SERVER_PORT = 8678;
    private static Set<String> names = new HashSet<>();
    private static List<PrintWriter> writers = new ArrayList<>();


    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while(true) {
                System.out.println("Жду соеденения");
                new Handler(serverSocket.accept()).start();
                System.out.println("Соединение получено");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        public Handler(Socket accept) {
            this.socket = accept;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                while (name == null) {
                    out.println("SUBMIT");
                    name = in.readLine();
                    if (name == null) {
                        return;
                    }
                    synchronized (names) {
                        if (!names.contains(name)) {
                            names.add(name);
                            System.out.println("New user " + name);
                        }
                    }
                }

                out.println("NAMEACCEPTED");
                writers.add(out);

                while (socket.isConnected()) {
                    String input = in.readLine();
                    System.out.println("wait");
                    if (input == null) {
                        return;
                    }
                    for (PrintWriter writer : writers) {
                        writer.println("MESSAGE " + name + ": " + input);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                if (name != null) {
                    names.remove(name);
                }
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }
    }
}




