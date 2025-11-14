package com.example.POS.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Book extends CommonEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long Id;

    @Column(name = "bookName", nullable = false, length = 50, unique = true)
    private String bookName;

    @Column(name = "bookStock", nullable = false)
    private int bookStock;

    @Column(name = "bookPrice", nullable = false)
    private Double bookPrice;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookStock() {
        return bookStock;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }


}
