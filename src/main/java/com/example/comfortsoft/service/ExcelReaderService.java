package com.example.comfortsoft.service;

import java.util.Set;

public interface ExcelReaderService {
    Set<Integer> readIntegersFromFirstColumn(String filePath);
}
