package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelMergeSort <T extends Comparable<T>> extends RecursiveTask<List<T>> {
    List<T> list;

    public ParallelMergeSort(List<T> list) {
        this.list = list;
    }

    @Override
    protected List<T> compute() {
        int size = list.size();
        if (size <= 1) {
            return list;
        }
        int middle = size / 2;
        List<T> leftList = new ArrayList<>(list.subList(0, middle));
        List<T> rightList = new ArrayList<>(list.subList(middle, size));
        ParallelMergeSort<T> leftSort = new ParallelMergeSort<>(leftList);
        ParallelMergeSort<T> rightSort = new ParallelMergeSort<>(rightList);

        invokeAll(leftSort, rightSort);
        return merge(leftSort.join(), rightSort.join());

//        leftSort.fork();
//        rightSort.fork();
//        return merge(leftSort.join(), rightSort.join());

//        leftSort.fork();
//        List<T> rightResult = rightSort.compute();
//        List<T> leftResult = leftSort.join();
//        return merge(leftResult, rightResult);
    }

    private static <T extends Comparable<T>> List<T> merge(List<T> leftList, List<T> rightList) {
        List<T> mergedList = new ArrayList<>();
        int i = 0;
        int j = 0;
        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).compareTo(rightList.get(j)) <= 0) {
                mergedList.add(leftList.get(i));
                i++;
            } else {
                mergedList.add(rightList.get(j));
                j++;
            }
        }
        while(i < leftList.size()){
            mergedList.add(leftList.get(i));
            i++;
        }
        while(j < rightList.size()){
            mergedList.add(rightList.get(j));
            j++;
        }
        return mergedList;
    }

    public static <T extends Comparable<T>> List<T> sort(List<T> inputList) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ParallelMergeSort<T> task = new ParallelMergeSort<>(inputList);
        return forkJoinPool.invoke(task);
    }
}
