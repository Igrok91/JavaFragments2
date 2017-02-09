package parserXml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by innopolis on 12.12.2016.
 */
public class SerializeImpl implements  Serialize{


    @Override
    public void  doSerialize(Object arg) {
        try {
            Class cl = arg.getClass();
            Field[] fields = cl.getDeclaredFields();

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = builder.newDocument();
           /* Element root = doc.createElement("root");
            doc.appendChild(root);*/

            Element objElement = doc.createElement("object");
            Attr att = doc.createAttribute("type");
            att.setValue(cl.getCanonicalName());
            objElement.setAttributeNode(att);
            doc.appendChild(objElement);

            // Следующий элемент
            for (Field f : fields) {
                f.setAccessible(true);
                Element element2 = doc.createElement("field");
                element2.setAttribute("type" , f.getType().getName());
                element2.setAttribute("id", f.getName());
                element2.setAttribute("value", f.get(arg).toString());
                objElement.appendChild(element2);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource dom = new DOMSource(doc);
            File file = new File("C:\\Main\\Task\\file.txt");
            StreamResult streamResult = new StreamResult(file);
            transformer.transform(dom, streamResult);
            System.out.println("File saved");

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
