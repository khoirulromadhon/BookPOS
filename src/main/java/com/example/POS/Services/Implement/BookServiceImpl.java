package com.example.POS.Services.Implement;

import com.example.POS.Models.Book;
import com.example.POS.Repositories.BookRepository;
import com.example.POS.Services.Interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBook() {
        try {
            return this.bookRepository.GetAllBookNotDeleted();
        }
        catch (Exception e){
            System.out.println("Error Get All Book: " + e.getMessage());
            return null;
        }
    }

    @Override
    public Book updateBook(Book book) {
        try {
            Book bookUpdate = this.bookRepository.findById(book.getId()).orElse(null);
            bookUpdate.setBookName(book.getBookName());
            bookUpdate.setBookPrice(book.getBookPrice());
            bookUpdate.setBookStock(book.getBookStock());
            bookUpdate.setModifiedOn(new Date());
            bookUpdate.setModifiedBy("user");
            this.bookRepository.save(bookUpdate);
        }
        catch (Exception e) {
            System.out.println("Error Update Book: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void insertBook(Book book) {
        try {
            book.setCreatedOn(new Date());
            book.setCreatedBy("user");
            this.bookRepository.save(book);
        }
        catch (Exception e) {
            System.out.println("Error Insert Book: " + e.getMessage());
        }
    }

    @Override
    public void deleteBook(Long id) {
        try {
            Book bookDelete = this.bookRepository.findById(id).orElse(null);
            bookDelete.setDeleteOn(new Date());
            bookDelete.setDeleteBy("user");
            bookDelete.setDelete(true);
            bookRepository.save(bookDelete);
        }
        catch (Exception e) {
            System.out.println("Error Insert Book: " + e.getMessage());
        }
    }
}
