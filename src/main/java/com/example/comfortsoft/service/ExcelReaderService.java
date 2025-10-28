package com.example.comfortsoft.service;

import java.util.List;

public interface ExcelReaderService {
    List<Integer> readIntegersFromFirstColumn(String filePath);
}
