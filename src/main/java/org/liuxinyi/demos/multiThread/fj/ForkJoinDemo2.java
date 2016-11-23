package org.liuxinyi.demos.multiThread.fj;

import lombok.extern.slf4j.Slf4j;
import org.liuxinyi.utils.common.DiceResult;
import org.liuxinyi.utils.common.DiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Eric.Liu on 2016/11/23.
 */
@Slf4j
public class ForkJoinDemo2 extends RecursiveTask<Long> {
    private static final int THRESHOLD = 4;

    private int start;
    private int end;
    private List<Integer> dataList;

    public ForkJoinDemo2(List<Integer> dataList) {
        this.start = 0;
        this.end = dataList.size() - 1;
        this.dataList = dataList;
    }

    public ForkJoinDemo2(int start, int end, List<Integer> dataList) {
        this.start = start;
        this.end = end;
        this.dataList = dataList;
    }

    public static void main(String[] args) {
        int total = 10;
        List<Integer> integers = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            integers.add(i);
        }

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo2 demo2 = new ForkJoinDemo2(integers);
        ForkJoinTask<Long> result = forkJoinPool.submit(demo2);
        try {
            long sum = result.get();
            log.info("sum : {} ", sum);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    @Override
    protected Long compute() {
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            long sum = 0;
            for (int i = start; i <= end; i++) {
                sum += dataList.get(i);
            }
            return sum;
        } else {
            long sum = 0;
            List<DiceResult> diceResults = DiceUtils.diceByTotal(dataList.size(), THRESHOLD);
            log.info("diceResults : {} ", diceResults);
            List<ForkJoinDemo2> subTasks = new ArrayList<>(diceResults.size());
            for (DiceResult diceResult : diceResults) {
                ForkJoinDemo2 subTask = new ForkJoinDemo2(diceResult.getStart(), diceResult.getEnd(), dataList);
                subTasks.add(subTask);
                subTask.fork();
            }
            for (ForkJoinDemo2 t : subTasks) {
                sum += t.join();
            }
            return sum;
        }
    }
}
