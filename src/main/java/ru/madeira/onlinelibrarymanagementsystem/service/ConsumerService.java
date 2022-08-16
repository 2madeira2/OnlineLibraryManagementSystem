package ru.madeira.onlinelibrarymanagementsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.madeira.onlinelibrarymanagementsystem.entity.Book;

import java.util.List;

@Service
public class ConsumerService {

    private final BookService bookService;
    private final XmlMapper xmlMapper;

    public ConsumerService (BookService bookService) {
        this.bookService = bookService;
        this.xmlMapper = XmlMapper.builder().addModule(new JavaTimeModule()).build();
    }

    @KafkaListener(topics = "books", groupId = "booksUpdaters")
    public void updateBooksInLibrary(String message) throws JsonProcessingException {
        xmlMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        List<Book> books = xmlMapper.readValue(message, new TypeReference<>() {});
        bookService.addBooks(books);
        //jaxb, jaxws, wsdl, xslt, dom, два подхода, sax simple api for xml, xpath
    }
}
