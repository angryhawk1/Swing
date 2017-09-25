package de.swing.Executer;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class ArraySumCalculator implements Runnable{

    ConcurrentHashMap<String, Integer> sumMap;
    List<Integer> integers;
    String arrayName;

    ArraySumCalculator(ConcurrentHashMap<String, Integer> sumMap,
                       List<Integer> integers,
                       String arrayName){
        this.sumMap = sumMap;
        this.integers = integers;
        this.arrayName = arrayName;
    }

    @Override
    public void run() {
        int sum = 0;
        for ( Integer i : integers){
            sum += i;
        }
        sumMap.put(arrayName, sum);
    }
}