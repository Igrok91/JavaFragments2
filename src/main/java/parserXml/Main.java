package parserXml;

import java.io.File;

/**
 * Created by innopolis on 12.12.2016.
 * Реализовать программу, обладающую следующим функционалом:
 •	Десериализация объектов из xml и печать их в консоль.
 •	Сериализация объектов в xml

 */
public class Main {
    public static void main(String[] args) {
      /*  Student s = new Student();
        s.setAge("25");
        s.setName("Igor");
        Serialize ser = new SerializeImpl();
        ser.doSerialize(s);*/

        File file = new File("C:\\Main\\Task\\file.txt");
        DeSerialize deSer = new DeSerializeImpl();
        Student student = (Student)deSer.doDeserialize(file);
        System.out.println(student.getAge());
        System.out.println(student.getName());

    }
}
