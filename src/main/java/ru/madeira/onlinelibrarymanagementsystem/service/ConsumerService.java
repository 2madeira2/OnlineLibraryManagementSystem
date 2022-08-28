package ru.madeira.onlinelibrarymanagementsystem.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.entity.Author;
import ru.madeira.onlinelibrarymanagementsystem.util.xml.wrapper.Books;
import ru.madeira.onlinelibrarymanagementsystem.util.xml.XmlParser;

import javax.xml.bind.JAXBException;

@Service
public class ConsumerService {

    private final BookService bookService;
    private final XmlMapper xmlMapper;
    private final XmlParser xmlParser;

    public ConsumerService(BookService bookService, XmlParser xmlParser) {
        this.bookService = bookService;
        this.xmlParser = xmlParser;
        this.xmlMapper = XmlMapper.builder().addModule(new JavaTimeModule()).build();
    }

    @KafkaListener(topics = "books", groupId = "booksUpdaters")
    public void updateBooksInLibrary(String message) throws JAXBException {
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        Books books = (Books) xmlParser.getParseObjectFromXml(message, Books.class, Author.class);
        bookService.addBooks(books.getBooks());
//        List<Book> books = xmlMapper.readValue(message, new TypeReference<>() {});
//        bookService.addBooks(books);

        //jaxb, jaxws, wsdl, xslt, dom, два подхода, sax simple api for xml, xpath
    }
}
