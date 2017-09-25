package de.swing.Executer;

import java.util.List;
import java.util.concurrent.Callable;

class ArraySumCallable implements Callable<Integer> {

    List<Integer> integers;

    ArraySumCallable(List<Integer> integers){

        this.integers = integers;
    }

    @Override
    public Integer call() throws Exception {

        Integer sum = 0;

        for (Integer i : integers){
            sum += i;
        }

        return sum;

    }
}