package com.example.POS.Configuration;

import com.example.POS.Repositories.BookRepository;
import com.example.POS.Services.Implement.BookServiceImpl;
import com.example.POS.Services.Interfaces.BookService;
import org.springframework.context.annotation.Bean;

public class BeanConfiguration {
    @Bean
    BookService bookService(BookRepository bookRepository){
        return new BookServiceImpl(bookRepository);
    }
}
