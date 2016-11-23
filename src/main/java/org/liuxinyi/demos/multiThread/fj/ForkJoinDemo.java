package org.liuxinyi.demos.multiThread.fj;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.FastArrayList;
import org.liuxinyi.utils.common.DiceResult;
import org.liuxinyi.utils.common.DiceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by Eric.Liu on 2016/11/22.
 */
@Slf4j
public class ForkJoinDemo extends RecursiveTask<Long> {


    private int start;
    private int end;
    private List<Integer> list;

    public ForkJoinDemo(int start, int end, List<Integer> list) {
        this.start = start;
        this.end = end;
        this.list = list;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int total = 10;
        int threshold = 3;
        List<Integer> dataList = new ArrayList<>(total);
        for (int i = 0; i < total; i++) {
            dataList.add(i);
        }
        List<DiceResult> diceResults = DiceUtils.diceByTotal(total, threshold);
        log.info("diceResults : {}", diceResults);
        List<ForkJoinDemo> forkJoinDemoList = new FastArrayList();
        for (DiceResult diceResult : diceResults) {
            ForkJoinDemo demo = new ForkJoinDemo(diceResult.getStart(), diceResult.getEnd(), dataList);
            forkJoinDemoList.add(demo);
        }

        long sum = 0;
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        for (ForkJoinDemo demo : forkJoinDemoList) {
            sum += forkJoinPool.invoke(demo);
        }
        log.info("sum : {}", sum);
    }
}
