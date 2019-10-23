package com.mmal.concurrency.AQS;

import java.util.concurrent.RecursiveTask;

public class ForkJoinExample extends RecursiveTask<Integer> {

    public static final int threshold = 2;

    private int start;
    private int end;

    public ForkJoinExample(int start, int end){
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;

        // 如果任务足够小就计算任务
        boolean canCompute = (end - start ) <= threshold;
        if(canCompute){
            for(int i = start; i <= end; i++){
                sum += i;
            }
        }else {
            // 若果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            ForkJoinExample leftTask = new ForkJoinExample(start,middle);
            ForkJoinExample rightTask = new ForkJoinExample(middle+1, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            // d等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            // 合并子任务
            sum = leftResult+rightResult;
        }
        return sum;
    }


    public static void main(String[] args) {
        ForkJoinExample forkJoinExample = new ForkJoinExample(1,2);
        Integer compute = forkJoinExample.compute();
        System.out.println(compute);
    }
}
