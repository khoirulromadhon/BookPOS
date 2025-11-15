package com.example.POS.Repositories;

import com.example.POS.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books b WHERE b.is_delete = false", nativeQuery = true)
    List<Book> GetAllBookNotDeleted();
}
