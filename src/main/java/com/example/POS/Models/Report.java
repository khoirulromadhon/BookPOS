package com.example.POS.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "report")
public class Report extends CommonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long Id;

    @Column(name = "bookId")
    private Long bookId;

    @ManyToOne
    @JoinColumn(name = "bookId", insertable = false, updatable = false)
    public Book book;

    @Column(name = "soldAmount")
    private int soldAmount;

    @Column(name = "profitSales")
    private Double profitSales;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(int soldAmount) {
        this.soldAmount = soldAmount;
    }

    public Double getProfitSales() {
        return profitSales;
    }

    public void setProfitSales(Double profitSales) {
        this.profitSales = profitSales;
    }
}
