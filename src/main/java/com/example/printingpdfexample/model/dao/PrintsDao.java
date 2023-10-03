package com.example.printingpdfexample.model.dao;

import com.example.printingpdfexample.model.enums.PrintStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prints", schema = "data")
public class PrintsDao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String fileName;
    private String printerName;
    private Long fileSize;
    private OffsetDateTime printedAt;
    private byte[] fileData;
    @Enumerated(EnumType.STRING)
    private PrintStatusEnum status;
}
