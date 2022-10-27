package com.example.seerbit.AlgorithmSolutions;

import java.util.Arrays;
import java.util.List;

public class IndexPosition {
    public static void main(String[] args) {

        List<Integer> arrayList = Arrays.asList(1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 6, 6);
        IndexPosition index = new IndexPosition();
        System.out.println(index.highIndex(arrayList,2));
        System.out.println(index.lowIndex(arrayList,2));
    }

    // Given a sorted array of integers, return the low and high index of the given key. Return
    //-1 if not found. The array length can be in the millions with many duplicates.

    /*
    Since the data can run into millions, a more optimized search (binary search) must be used
    Binary search complexity analysis:
    Time complexity: O(log n)
    Space complexity: 0(1)
     */
    public int lowIndex (List<Integer> arr, int key) {
        int lowVal = 0;
        int highVal = arr.size() - 1;
        int midVal = highVal / 2;

        while (lowVal <= highVal) {
            int mid_element = arr.get(midVal);
            if (mid_element < key) {
                lowVal = midVal + 1;
            } else {
                highVal = midVal - 1;
            }
            midVal = lowVal + (highVal - lowVal) / 2;
        }
        if (lowVal < arr.size() && arr.get(lowVal) == key) {
            return lowVal;
        }
        return -1;
    }

    public int highIndex(List<Integer> array, int key) {
        int lowVal = 0;
        int highVal = array.size() - 1;
        int midVal = highVal / 2;
        while (lowVal <= highVal) {
            int mid_elem = array.get(midVal);
            if (mid_elem <= key) {
                lowVal = midVal + 1;
            } else {
                highVal = midVal - 1;
            }
            midVal = lowVal + (highVal - lowVal) / 2;
        }
        if(highVal == -1){
            return highVal;
        }
        if (array.get(highVal) == key) {
            return highVal;
        }
        return -1;
    }
}
