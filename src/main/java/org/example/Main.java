package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Integer newInt = (int) (Math.random()*1000000);
            intList.add(newInt);
        }

        long time = System.currentTimeMillis();
        List<Integer> parallelSortedList = ParallelMergeSort.sort(intList);
//        ParallelMergeSort<Integer> parallelMergeSort = new ParallelMergeSort<>(intList);
//        List<Integer> compute = parallelMergeSort.compute();
        System.out.println("Parallel Sort with ForkJoinPool: " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        List<Integer> simpleSortedList = MyMergeSort.sort(intList);
        System.out.println("Simple merge Sort: " + (System.currentTimeMillis() - time));
    }
}