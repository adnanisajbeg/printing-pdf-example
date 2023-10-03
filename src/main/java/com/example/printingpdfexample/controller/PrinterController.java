package com.example.printingpdfexample.controller;

import com.example.printingpdfexample.service.PrinterService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class PrinterController {
    private final PrinterService printerService;

    @GetMapping("/printers")
    public List<String> printers() {
        return printerService.showPrinters();
    }

    @PostMapping("/print")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("printerName") String printerName) {

        try {
            return printerService.print(file, printerName);
        } catch (IOException e) {
            log.error("Error printing file", e);
            return "Error printing file";
        }
    }
}
