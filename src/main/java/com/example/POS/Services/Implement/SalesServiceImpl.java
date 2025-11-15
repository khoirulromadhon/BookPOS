package com.example.POS.Services.Implement;

import com.example.POS.Models.Book;
import com.example.POS.Models.Report;
import com.example.POS.Models.Sales;
import com.example.POS.Repositories.BookRepository;
import com.example.POS.Repositories.ReportRepository;
import com.example.POS.Repositories.SalesRepository;
import com.example.POS.Services.Interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SalesServiceImpl implements SalesService {
    @Autowired
    SalesRepository salesRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReportRepository reportRepository;

    public SalesServiceImpl(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    @Override
    public Object Checkout(Sales sales) {
        Map<String, String> result = new HashMap<>();

        try {
            Book book = this.bookRepository.findById(sales.getBookId()).orElse(null);

            if (book != null) {
                if (sales.getBookAmount() > book.getBookStock()) {
                    result.put("Message", "Maaf, Stock Tidak Mencukupi !!!");
                    return result;
                }
                else {
                    sales.setCreatedBy("user");
                    sales.setCreatedOn(new Date());
                    this.salesRepository.save(sales);

                    Long bookId = sales.getBookId();

                    Report currentReport = this.reportRepository.GetBookReport(bookId);
                    Report insertReport = new Report();

                    if (currentReport != null) {
                        if (sales.getStatus().equalsIgnoreCase("SUKSES")) {
                            Book bookUpdateStock = this.bookRepository.findById(sales.getBookId()).orElse(null);
                            bookUpdateStock.setBookStock(bookUpdateStock.getBookStock() - sales.getBookAmount());
                            this.bookRepository.save(bookUpdateStock);

                            insertReport.setId(currentReport.getId());
                            insertReport.setBookId(currentReport.getBookId());
                            insertReport.setSoldAmount(currentReport.getSoldAmount() + sales.getBookAmount());
                            insertReport.setProfitSales(currentReport.getProfitSales() + sales.getPriceAmount());
                            insertReport.setCreatedBy("system");
                            insertReport.setCreatedOn(new Date());
                            this.reportRepository.save(insertReport);

                            result.put("Message", "Pembelian Berhasil !!!");
                            return result;
                        }
                        else if (sales.getStatus().equalsIgnoreCase("PENDING")) {
                            Book bookUpdateStock = this.bookRepository.findById(sales.getBookId()).orElse(null);
                            bookUpdateStock.setBookStock(bookUpdateStock.getBookStock() - sales.getBookAmount());
                            this.bookRepository.save(bookUpdateStock);

                            result.put("Message", "Mohon Tunggu, Status Transaksi Anda Masih Pending !!!");
                            return result;
                        }
                        else if (sales.getStatus().equalsIgnoreCase("GAGAL")) {
                            result.put("Message", "Mohon Maaf, Status Transaksi Anda Gagal !!!");
                            return result;
                        }
                        else {
                            result.put("Message", "Invalid Checkout Status !!!");
                            return result;
                        }
                    }
                    else {
                        insertReport.setBookId(sales.getBookId());
                        insertReport.setSoldAmount(sales.getBookAmount());
                        insertReport.setProfitSales(sales.getPriceAmount());
                        insertReport.setCreatedBy("system");
                        insertReport.setCreatedOn(new Date());
                        this.reportRepository.save(insertReport);

                        Book bookUpdateStock = this.bookRepository.findById(sales.getBookId()).orElse(null);
                        bookUpdateStock.setBookStock(bookUpdateStock.getBookStock() - sales.getBookAmount());
                        this.bookRepository.save(bookUpdateStock);

                        result.put("Message", "Pembelian Berhasil !!!");
                        return result;
                    }
                }
            }
            else {
                result.put("Message", "Buku yang anda cari tidak ada !!!");
                return result;
            }
        }
        catch (Exception e) {
            System.out.println("Error Checkout: " + e.getMessage());
            result.put("Message", e.getMessage().toString());
            return result;
        }
    }
}
