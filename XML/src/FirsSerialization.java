import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.IOException;
import java.io.StringWriter;

/* 
My first serialization into XML
Correctly arrange JAXB annotations on the static classes.

 Expected output:
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<cat>
    <name>Missy</name>
    <age>5</age>
    <weight>3</weight>
</cat>
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<dog>
    <name>Killer</name>
    <age>8</age>
    <owner>Bill Jefferson</owner>
</dog>


http://search.maven.org/remotecontent?filepath=com/sun/activation/javax.activation/1.2.0/javax.activation-1.2.0.jar
http://search.maven.org/remotecontent?filepath=javax/xml/bind/jaxb-api/2.3.0/jaxb-api-2.3.0.jar
http://search.maven.org/remotecontent?filepath=com/sun/xml/bind/jaxb-core/2.3.0/jaxb-core-2.3.0.jar
http://search.maven.org/remotecontent?filepath=com/sun/xml/bind/jaxb-impl/2.3.0/jaxb-impl-2.3.0.jar
*/
public class FirsSerialization {
    public static void main(String[] args) throws IOException, JAXBException {
        Cat cat = new Cat();
        cat.name = "Missy";
        cat.age = 5;
        cat.weight = 3;

        Dog dog = new Dog();
        dog.name = "Killer";
        dog.age = 8;
        dog.owner = "Bill Jefferson";

        StringWriter writer = new StringWriter();
        convertToXml(writer, cat);
        convertToXml(writer, dog);
        System.out.println(writer.toString());

    }

    public static void convertToXml(StringWriter writer, Object obj) throws IOException, JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(obj, writer);
    }

    public static class Pet {
        public String name;
    }
    @XmlRootElement
    @XmlType(name = "cat")
    public static class Cat extends Pet {
        public int age;
        public int weight;
    }@XmlRootElement
   @XmlType(name = "dog")
    public static class Dog extends Pet {
        public int age;
        public String owner;
    }
}