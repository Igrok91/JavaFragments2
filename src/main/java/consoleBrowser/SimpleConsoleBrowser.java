package consoleBrowser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by innopolis on 21.12.2016.
 */
public class SimpleConsoleBrowser {
    //private static Logger log = LoggerFactory.getLogger(SimpleConsoleBrowser.class);

    public static void main(String[] args) throws MalformedURLException {
        System.out.println("Введите URl");
        String urlString = getUrl();

            URL url = new URL(urlString);

           try(BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
              String s;
              while ((s = reader.readLine()) != null) {
                  System.out.println(s);
              }
              //log.info("Success");
           } catch (IOException e) {
            //  log.warn(e.getMessage());
           }


    }

    private static String getUrl() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String url = reader.readLine();
            return url;
        } catch (IOException e) {
           // log.warn(e.getMessage());
        }
        return null;
    }
}
