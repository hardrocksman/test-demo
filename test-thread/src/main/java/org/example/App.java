package org.example;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("process-thread-%d").build();
//        ExecutorService pool = new ThreadPoolExecutor(2, 3, 600000,
//                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("process-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2, 3, 600000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2), namedThreadFactory, new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        if (!executor.isShutdown()) {
                            try {
                                executor.getQueue().put(r);
                            } catch (InterruptedException e) {
                                // should not be interrupted
                            }
                        }
                    }
            });

        for (int i = 0; i < 5; i++) {
            pool.submit(new TestCallable(i));
        }

        for (int i = 5; i < 10; i++) {
            pool.submit(new TestShortCallable(i));
        }
    }
}
