package com.example.printingpdfexample.service;

import com.example.printingpdfexample.model.dao.PrintsDao;
import com.example.printingpdfexample.model.enums.PrintStatusEnum;
import com.example.printingpdfexample.repository.PrintsRepository;
import com.example.printingpdfexample.utils.PrinterUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.*;

import java.awt.print.PrinterJob;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PrinterService {
    private final PrintsRepository printsRepository;

    public List<String> showPrinters() {
        return PrinterUtils.getPrinterServiceNameList();
    }

    public String print(MultipartFile file, String printerName) throws IOException {
        var printDao = new PrintsDao().builder()
                .status(PrintStatusEnum.IN_PROGRESS)
                .fileName(file.getName())
                .fileSize(file.getSize())
                .printedAt(OffsetDateTime.now())
                .printerName(printerName)
                .fileData(file.getBytes())
                .build();
        PrintsDao savedPrintsDao = printsRepository.save(printDao);

        PrintService service = PrinterUtils.findPrintService(printerName);

        if (service == null) {
            savedPrintsDao.setStatus(PrintStatusEnum.FAILED);
            printsRepository.save(printDao);
            log.error("Printer not found");
            return "Printer not found";

        }

        PrinterJob job = PrinterJob.getPrinterJob();

        try {
            PDDocument document = Loader.loadPDF(file.getBytes());
            job.setPrintService(service);
            job.setPageable(new PDFPageable(document));
            job.print();

        } catch (Exception e) {
            savedPrintsDao.setStatus(PrintStatusEnum.FAILED);
            printsRepository.save(printDao);
            log.error("Issue with printing: {}", e.getMessage());
            return "Failed to print";
        }

        savedPrintsDao.setStatus(PrintStatusEnum.SUCCESS);
        printsRepository.save(printDao);

        return "Printed";
    }
}
