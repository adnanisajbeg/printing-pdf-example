package com.example.printingpdfexample.repository;

import com.example.printingpdfexample.model.dao.PrintsDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrintsRepository extends JpaRepository<PrintsDao, UUID> {
}
