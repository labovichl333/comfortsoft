package com.example.comfortsoft.service.impl;

import com.example.comfortsoft.service.NumberSearchService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class QuickNumberSearchService implements NumberSearchService {

    private final Random random = new Random();

    @Override
    public int searchKMinNumber(int[] arr, int k) {
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k must be between 1 and list size");
        }
        return quickSelect(arr, 0, arr.length - 1, k - 1);
    }

    private int quickSelect(int[] arr, int left, int right, int k) {
        while (left < right) {
            int pivotIndex = random.nextInt(right - left + 1) + left;
            pivotIndex = partition(arr, left, right, pivotIndex);
            if (k == pivotIndex) {
                return arr[k];
            } else if (k < pivotIndex) {
                right = pivotIndex - 1;
            } else {
                left = pivotIndex + 1;
            }
        }
        return arr[left];
    }

    private int partition(int[] arr, int left, int right, int pivotIndex) {
        int pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
