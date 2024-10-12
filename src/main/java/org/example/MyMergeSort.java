package org.example;

import java.util.ArrayList;
import java.util.List;

public class MyMergeSort<T extends Comparable<T>> {
    List<T> inputList;

    public MyMergeSort(List<T> inputList) {
        this.inputList = inputList;
    }

    public static <T extends Comparable<T>> List<T> sort(List<T> inputList) {
        int size = inputList.size();
        if (size <= 1) {
            return inputList;
        }
        int middle = size / 2;
        List<T> leftList = new ArrayList<>(inputList.subList(0, middle));
        List<T> rightList = new ArrayList<>(inputList.subList(middle, size));
        leftList = sort(leftList);
        rightList = sort(rightList);
        return merge(leftList, rightList);
    }

    private static  <T extends Comparable<T>> List<T> merge(List<T> leftList, List<T> rightList) {
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
}
