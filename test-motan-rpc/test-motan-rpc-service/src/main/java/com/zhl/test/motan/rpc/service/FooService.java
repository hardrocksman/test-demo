package com.zhl.test.motan.rpc.service;

import com.zhl.test.motan.rpc.IFooService;

/**
 * Created by Administrator on 2016/5/10.
 */
public class FooService implements IFooService {

    @Override
    public String hello(String name) {
        return "hello: " + name;
    }
}
