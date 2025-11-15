package com.example.POS.Constrollers;

import com.example.POS.Models.Report;
import com.example.POS.Services.Interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("report")
@CrossOrigin("*")
public class ReportController {
    @Autowired private ReportService reportService;

    @GetMapping("get-report")
    ResponseEntity<List<Report>> GetReport(){
        try {
            List<Report> reports = this.reportService.GetSalesReport();
            return new ResponseEntity<>(reports, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
