package com.example.printingpdfexample.controller;

import com.example.printingpdfexample.service.PrinterService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PrinterController {
    private final PrinterService printerService;

    @GetMapping("/printers")
    public List<String> printers() {
        return printerService.showPrinters();
    }
}
