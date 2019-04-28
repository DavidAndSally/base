package com.jd.pop.base.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Demo，线程池对象
 *
 * @author qiw-a
 * @date 2019/4/18
 */
public class DemoExecutorService {

    private DemoExecutorService() {
    }

    /**
     * 线程池对象
     */
    private static final ExecutorService EXEC;
    /**
     * 线程池核心线程数: 5
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 线程池最大线程数: 10
     */
    private static final int MAXIMUM_POOL_SIZE = 10;
    /**
     * 线程空闲时间: 60
     */
    private static final long KEEP_ALIVE_TIME = 60;
    /**
     * 线程空闲时间单位: 秒
     */
    private static final TimeUnit UNIT = TimeUnit.SECONDS;
    /**
     * 线程池阻塞队列：LinkedBlockingQueue，长度300
     */
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<>(300);
    /**
     * 线程池线程工厂对象
     */
    private static final ThreadFactory THREAD_FACTORY = new DemoThreadFactory();
    /**
     * 线程池拒绝策略：调用线程执行该线程
     */
    private static final RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

    static {
        EXEC = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE,
                KEEP_ALIVE_TIME, UNIT, WORK_QUEUE, THREAD_FACTORY, HANDLER);
    }

    /**
     * 咨询线程池，创建线程工厂
     */
    private static class DemoThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DemoThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "bim5d-demo-pool-" +
                    POOL_NUMBER.getAndIncrement() +
                    "-bim5d-demo-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }

    /**
     * 提交任务方法
     *
     * @param callable callable任务对象
     * @param <T>      任务执行结果
     * @return Future<T>
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return EXEC.submit(callable);
    }

    /**
     * 执行任务方法
     *
     * @param runnable runnable任务对象
     */
    public static void execute(Runnable runnable) {
        EXEC.execute(runnable);
    }
}
