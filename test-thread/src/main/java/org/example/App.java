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
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("process-thread-%d").build();
        ExecutorService pool = new ThreadPoolExecutor(2, 3, 600000,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(2), namedThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

        for (int i = 0; i < 10; i++) {
            pool.submit(new TestCallable(i));
        }
    }
}
