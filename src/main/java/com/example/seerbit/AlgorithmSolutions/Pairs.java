package com.example.seerbit.AlgorithmSolutions;

import java.util.ArrayList;

class Pair{
    public int first;
    public int second;

    public Pair(int x, int y){
        this.first = x;
        this.second = y;
    }
}

class MergeIntervals{
    static ArrayList<Pair> mergeIntervals(ArrayList<Pair> value) {

        if(value == null || value.size() == 0) {
            return null;
        }

        ArrayList<Pair> result = new ArrayList<Pair>();

        result.add(new Pair(value.get(0).first, value.get(0).second));

        for(int i = 1 ; i < value.size(); i++) {
            int x1 = value.get(i).first;
            int y1 = value.get(i).second;
            int x2 = result.get(result.size() - 1).first;
            int y2 = result.get(result.size() - 1).second;

            if(y2 >= x1) {
                result.get(result.size() - 1).second = Math.max(y1, y2);
            } else {
                result.add(new Pair(x1, y1));
            }
        }

        return result;
    }
    public static void main(String[] args) {
        ArrayList<Pair> v = new ArrayList<>();

        v.add(new Pair(1, 5));
        v.add(new Pair(3, 7));
        v.add(new Pair(4, 6));
        v.add(new Pair(6, 8));
        v.add(new Pair(10, 12));
        v.add(new Pair(11, 15));

        ArrayList<Pair> result = mergeIntervals(v);

        for(int i=0; i<result.size(); i++){
            System.out.print(String.format("[%d, %d] ", result.get(i).first, result.get(i).second));
        }
    }
}
/*
You are given an array (list) of interval pairs as input where each interval has a start
and end timestamp. The input array is sorted by starting timestamps. You are required
to merge overlapping intervals and return output array (list).

Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping
intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 */