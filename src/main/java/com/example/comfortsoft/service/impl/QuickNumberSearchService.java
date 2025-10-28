package com.example.comfortsoft.service.impl;

import com.example.comfortsoft.service.NumberSearchService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class QuickNumberSearchService implements NumberSearchService {

    private final Random random = new Random();

    @Override
    public int searchKMinNumber(List<Integer> list, int k) {
        if (k < 1 || k > list.size()) {
            throw new IllegalArgumentException("k must be between 1 and list size");
        }
        return quickSelect(list, 0, list.size() - 1, k - 1);
    }

    private int quickSelect(List<Integer> arr, int left, int right, int k) {
        if (left == right) {
            return arr.get(left);
        }

        int pivotIndex = random.nextInt(right - left + 1) + left;
        pivotIndex = partition(arr, left, right, pivotIndex);

        if (k == pivotIndex) {
            return arr.get(k);
        } else if (k < pivotIndex) {
            return quickSelect(arr, left, pivotIndex - 1, k);
        } else {
            return quickSelect(arr, pivotIndex + 1, right, k);
        }
    }

    private int partition(List<Integer> arr, int left, int right, int pivotIndex) {
        int pivotValue = arr.get(pivotIndex);
        swap(arr, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < right; i++) {
            if (arr.get(i) < pivotValue) {
                swap(arr, storeIndex, i);
                storeIndex++;
            }
        }
        swap(arr, right, storeIndex);
        return storeIndex;
    }

    private void swap(List<Integer> arr, int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}
