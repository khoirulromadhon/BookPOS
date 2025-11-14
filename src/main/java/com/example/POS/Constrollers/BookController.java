package com.example.POS.Constrollers;

import com.example.POS.Models.Book;
import com.example.POS.Services.Interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("book")
@CrossOrigin("*")
public class BookController {
    @Autowired private BookService bookService;

    @PostMapping("insert")
    public ResponseEntity<Book> InsertBook(@RequestBody Book book) {
        try {
            this.bookService.insertBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    public ResponseEntity<List<Book>> GetAllBook() {
        try {
            return new ResponseEntity<>(this.bookService.getAllBook(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("update")
    public ResponseEntity<Book> UpdateBook(@RequestBody Book book) {
        try {
            this.bookService.updateBook(book);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> DeleteBook(@PathVariable Long id) {
        try {
            this.bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
