package com.example.POS.Constrollers;

import com.example.POS.Models.Sales;
import com.example.POS.Services.Interfaces.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sales")
@CrossOrigin("*")
public class SalesController {
    @Autowired private SalesService salesService;

    @PostMapping("generate-sales")
    public ResponseEntity<Object> GenerateSales(@RequestBody Sales sales) {
        try {
            return new ResponseEntity<>(this.salesService.Checkout(sales), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println("Error Generate Sales at Controller: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
