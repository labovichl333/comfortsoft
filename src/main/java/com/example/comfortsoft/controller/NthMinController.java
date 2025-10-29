package com.example.comfortsoft.controller;

import com.example.comfortsoft.exeption.InvalidInputException;
import com.example.comfortsoft.service.ExcelReaderService;
import com.example.comfortsoft.service.NumberSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "N-th Minimum Finder", description = "Find the N-th smallest integer in an Excel file")
@RequiredArgsConstructor
public class NthMinController {

    private final ExcelReaderService excelReaderServiceImpl;

    private final NumberSearchService quickNumberSearchService;

    @PostMapping("/nth-min")
    @Operation(
            summary = "Find N-th smallest number in an Excel file",
            description = "Reads integers from the first column of a local .xlsx file and returns the N-th smallest value."
    )

    public ResponseEntity<Integer> findNthMin(
            @Parameter(description = "Absolute path to .xlsx file", required = true)
            @RequestParam String filePath,

            @Parameter(description = "N-th smallest to find", required = true)
            @RequestParam int n
    ) {

        validateInputs(filePath, n);

        int[] numbers = excelReaderServiceImpl.readIntegersFromFirstColumn(filePath).stream()
                .mapToInt(Integer::intValue)
                .toArray();

        validateNumbersCount(numbers, n);

        int result = quickNumberSearchService.searchKMinNumber(numbers, n);

        return ResponseEntity.ok(result);
    }

    private void validateInputs(String filePath, int n) {
        if (filePath == null || filePath.isBlank()) {
            throw new InvalidInputException("File path must not be empty");
        }
        if (n < 1) {
            throw new InvalidInputException("N must be greater than 0");
        }
    }

    private void validateNumbersCount(int[] numbers, int n) {
        if (numbers.length == 0) {
            throw new InvalidInputException("The file does not contain any numbers");
        }
        if (n > numbers.length) {
            throw new InvalidInputException("N must not exceed the total number of integers in the file");
        }
    }

}
