package com.example.seerbit.AlgorithmSolutions;

import java.util.*;

//Given an array of integers and a value, determine if there are any two integers in the
//array whose sum is equal to the given value.
public class TwoNumberSum {
    public static void main(String[] args) {
        int[] myArray = {7, 0, 4, 2, 1, 5, 6, 9, 7};
        TwoNumberSum twoNumberSum = new TwoNumberSum();
        System.out.println((twoNumberSum.twoNumberSum(myArray, 14)));

    }

    public boolean twoNumberSum(int[] numbers, int target) {
        Map<Integer, Integer> maps = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            //add the complement of the real num into map as key and the index as value
            //if you find the key on another iteration, you have found your solution
            maps.put(target - numbers[i], i);
            Integer getComplementIndex = maps.get(numbers[i]);
            if (getComplementIndex != null) {
                return true;
            }
        }
        return false;
    }
}