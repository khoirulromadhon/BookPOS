package com.example.POS.Services.Implement;

import com.example.POS.Models.Report;
import com.example.POS.Repositories.ReportRepository;
import com.example.POS.Services.Interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public List<Report> GetSalesReport() {
        try {
            return this.reportRepository.findAll();
        }
        catch (Exception e){
            System.out.println("Error Report Service: " + e.getMessage());
        }
        return null;
    }
}
