package com.example.comfortsoft.service.impl;

import com.example.comfortsoft.exeption.FileFormatException;
import com.example.comfortsoft.exeption.FileProcessingException;
import com.example.comfortsoft.service.ExcelReaderService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderServiceImpl implements ExcelReaderService {

    private static final String XLSX_FILE_FORMAT = ".xlsx";

    @Override
    public List<Integer> readIntegersFromFirstColumn(String filePath) {

        if (!isValidFileFormat(filePath)) {
            throw new FileFormatException("Incorrect file format. The xlsx format is required.");
        }
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filePath);

             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    double value = cell.getNumericCellValue();
                    numbers.add((int) value);
                }
            }
            return numbers;
        } catch (FileNotFoundException e) {
            throw new FileProcessingException("The file was not found at this path");
        } catch (IOException e) {
            throw new FileProcessingException("An exception occurred while processing the file");
        }
    }

    private boolean isValidFileFormat(String filePath) {
        return filePath.toLowerCase().endsWith(XLSX_FILE_FORMAT);
    }
}