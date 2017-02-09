package classLoader.example1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by innopolis on 25.12.2016.
 */
public class MyClassLoader extends ClassLoader {

    public MyClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadClass(String nameClass, String nameFile) throws ClassNotFoundException {

        String url = String.format("file:C:\\SimpleConsoleBrowser\\Task\\%s.class", nameFile);
        try {
            URLConnection connection = new URL(url).openConnection();
            InputStream input = connection.getInputStream();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int data = input.read();

            while(data != -1){
                buffer.write(data);
                data = input.read();
            }

            input.close();

            byte[] classData = buffer.toByteArray();

            return this.defineClass(nameClass,
                    classData, 0, classData.length);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
