package ru.madeira.onlinelibrarymanagementsystem.util.xml;

import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.StringReader;

@Component
public class XmlParser {
    public Object getParseObjectFromXml(String xmlString, Class<?>... clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        return context.createUnmarshaller().unmarshal(new StringReader(xmlString));
    }
}
