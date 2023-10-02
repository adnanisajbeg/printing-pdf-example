package com.example.printingpdfexample.service;

import com.example.printingpdfexample.utils.PrinterUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PrinterService {
    public List<String> showPrinters() {
        return PrinterUtils.getPrinterServiceNameList();
    }
}
