package org.example;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

public class MyElasticSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext context) {
        switch (context.getShardingItem()) {
            case 0:
                System.out.println("do something by sharding item 0");
                break;
            case 1:
                System.out.println("do something by sharding item 1");
                break;
            case 2:
                System.out.println("do something by sharding item 2");
                break;
            // case n: ...
        }
    }
}
