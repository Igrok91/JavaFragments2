package parserXml;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by innopolis on 12.12.2016.
 */
public class DeSerializeImpl implements DeSerialize {
    Object instanceObject;
    @Override
    public Object doDeserialize(File file) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

                Node objList = doc.getElementsByTagName("object").item(0);

                String typeName = objList.getAttributes().getNamedItem("type").getNodeValue();
                System.out.println(typeName);
                Class cl = Class.forName(typeName);
                instanceObject = cl.newInstance();
                NodeList fieldList = doc.getElementsByTagName("field");
                for (int j = 0; j < fieldList.getLength(); j++) {
                   // System.out.println(fieldList.item(j).getNodeName());
                    if(!fieldList.item(j).getNodeName().equals("field")){
                        continue;
                    }
                    String typeField = fieldList.item(j).getAttributes().getNamedItem("id").getNodeValue();

                    String nameField = fieldList.item(j).getAttributes().getNamedItem("type").getNodeValue();

                    String valueField = fieldList.item(j).getAttributes().getNamedItem("value").getNodeValue();


                    for(Field field : instanceObject.getClass().getDeclaredFields()) {
                        System.out.println(field.getName());
                        System.out.println(typeField);
                        System.out.println(field.getType().getName());
                        if(field.getName().equals(typeField)) {

                            if(field.getType().getName().equals(nameField)) {

                                switch (field.getType().getSimpleName()) {
                                    case "String":
                                        field.setAccessible(true);
                                        field.set(instanceObject, valueField);
                                        break;
                                    case "Integer":
                                        field.setAccessible(true);
                                        field.set(instanceObject, new Integer(valueField));
                                        break;
                                    default:
                                        System.out.println(field.getType().getSimpleName());
                                        break;
                                }
                            }
                        }
                    }
                }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instanceObject;
    }
}
