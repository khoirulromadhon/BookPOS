package com.example.POS.Services.Interfaces;

import com.example.POS.Models.Book;

import java.util.List;

public interface BookService {
    public List<Book> getAllBook();

    public Book updateBook(Book book);

    public void insertBook(Book book);

    public void deleteBook(Long id);
}
