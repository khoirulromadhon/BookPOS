package com.example.POS.Configuration;

import com.example.POS.Repositories.BookRepository;
import com.example.POS.Repositories.ReportRepository;
import com.example.POS.Repositories.SalesRepository;
import com.example.POS.Services.Implement.BookServiceImpl;
import com.example.POS.Services.Implement.ReportServiceImpl;
import com.example.POS.Services.Implement.SalesServiceImpl;
import com.example.POS.Services.Interfaces.BookService;
import com.example.POS.Services.Interfaces.ReportService;
import com.example.POS.Services.Interfaces.SalesService;
import org.springframework.context.annotation.Bean;

public class BeanConfiguration {
    @Bean
    BookService bookService(BookRepository bookRepository){
        return new BookServiceImpl(bookRepository);
    }

    @Bean
    SalesService salesService(SalesRepository salesRepository) {
        return new SalesServiceImpl(salesRepository);
    }

    @Bean
    ReportService reportService(ReportRepository reportRepository) {
        return new ReportServiceImpl(reportRepository);
    }
}
