package com.example.xppdemo.chapter8;

import java.util.Map;
import java.util.concurrent.*;

/**
 * <p>Description: 银行流水计算</p>
 * <p>Copyright: Copyright (c)2,020</p>
 * <p>Company: tope</p>
 * <P>Created Date :2021/8/2</P>
 *
 * @author xiepanpan
 * @version 1.0
 */
public class BankWaterService implements Runnable {
    /**
     * 创建4个屏障，处理完之后执行当前类的run方法
     */
    private CyclicBarrier c = new CyclicBarrier(4, this);
    /**
     * 假设只有4个sheet，所以只启动4个线程
     */
    private Executor executor = Executors.newFixedThreadPool(4);
    /**
     * 保存每个sheet计算出的银流结果
     */
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new
            ConcurrentHashMap<String, Integer>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
// 计算当前sheet的银流数据，计算代码省略
                    sheetBankWaterCount
                            .put(Thread.currentThread().getName(), 1);
// 银流计算完成，插入一个屏障
                    try {
                        c.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        // 汇总每个sheet计算出的结果
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
// 将结果输出
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BankWaterService bankWaterCount = new BankWaterService();
        bankWaterCount.count();
    }
}
