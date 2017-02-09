package consoleBrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Created by innopolis on 21.12.2016.
 */
public class Main {
 //   private static Logger log = LoggerFactory.getLogger(Main.class);

    static {
        URL.setURLStreamHandlerFactory(new URLStreamHandlerFactory() {
            @Override
            public URLStreamHandler createURLStreamHandler(String protocol) {
                // фабрика умеет работать только с протоколом "classpath"
                if (!"classpath".equals(protocol))
                    return null;

                return new URLStreamHandler() {
                    @Override
                    protected URLConnection openConnection(URL u) throws IOException {
                        return new URLConnection(u) {
                            @Override
                            public void connect() throws IOException {
                            }

                            @Override
                            public InputStream getInputStream() throws IOException {
                                // получаю строку classpath
                                final String classPath = System.getProperty("java.class.path", ".");

                                // получаю папки из classpath
                                final String[] classPathElements = classPath.split(System.getProperty("path.separator"));

                                // имя файла, который будем искать
                                String fileName = getURL().getFile();

                                // пробегаю каждую папку и ищу файл
                                for (final String path : classPathElements) {
                                    File file = new File(path, fileName);
                                    // первый найденный файл возвращаю
                                    if (file.exists())
                                        return new FileInputStream(file);
                                }

                                // в classpath такой файл не найден
                                return null;
                            }
                        };
                    }
                };
            }
        });
    }

    public static void main(String[] args) {
        String url;
        // получаю ссылку на ресурс
        try (Scanner scan = new Scanner(System.in)) {
            System.out.print("> ");
//			url = "classpath:log4j.properties";
            url = scan.nextLine();
        }

        byte[] buf = new byte[0x200];
        int len;

        // открываю стрим, читаю и вывожу содержимое ресурса
        try (InputStream is = new URL(url).openStream()) {
            if (is == null)
                throw new RuntimeException("Stream not found");

            while ((len = is.read(buf)) > 0)
                System.out.print(new String(buf, 0, len));

            System.out.println();

           // log.info("Page transmit well done!");
        } catch (IOException e) {
           // log.error("Something went wrong :(", e);
        }
    }



}
