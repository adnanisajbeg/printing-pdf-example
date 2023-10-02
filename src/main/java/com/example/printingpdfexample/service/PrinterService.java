package com.example.printingpdfexample.service;

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
        // get list of all print services
        PrintService[] services = PrinterJob.lookupPrintServices();
        List<String> list = new ArrayList<String>();

        for (int i = 0; i < services.length; i++) {
            list.add(services[i].getName());
        }

        return list;
    }
}
