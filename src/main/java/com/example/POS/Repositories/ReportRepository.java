package com.example.POS.Repositories;

import com.example.POS.Models.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Long> {
    @Query(value = "SELECT * FROM report r WHERE r.book_id = :bookId", nativeQuery = true)
    Report GetBookReport(Long bookId);
}
