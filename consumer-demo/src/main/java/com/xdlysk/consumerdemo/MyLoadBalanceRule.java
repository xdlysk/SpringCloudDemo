package com.xdlysk.consumerdemo;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/***
 * 这里参考一下RoundRobinRule的源码
 * https://github.com/Netflix/ribbon/blob/master/ribbon-loadbalancer/src/main/java/com/netflix/loadbalancer/RoundRobinRule.java
 * 实现一个类似的（轮询）均衡策略
 */
public class MyLoadBalanceRule extends AbstractLoadBalancerRule {

    private static AtomicInteger seq = new AtomicInteger();

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
    }

    @Override
    public Server choose(Object o) {
        ILoadBalancer loadBalancer = getLoadBalancer();

        while (true){
            List<Server> reachableServers = loadBalancer.getReachableServers();
            int length = reachableServers.size();
            if(length==0){
                Thread.yield();
                continue;
            }
            return reachableServers.get(seq.getAndIncrement()%length);
        }
    }
}
