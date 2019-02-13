package edu.bit.hjs.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;


public class RandomRule_hjs extends AbstractLoadBalancerRule {

    //total = 0 -- 当total数等于5的时侯才能往下走
    //index = 0 -- 当前对外提供服务的服务器地址
    //当total = 5时,index需要加一,再把total置为0
    private int total = 0;
    private int currentIndex = 0;   //当前提供服务的机器号


    /**
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key){
        if(lb == null)
            return null;
        Server server = null;
        while(server == null){
            if (Thread.interrupted()){
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0)
                return null;
            if(total<5){
                server = upList.get(currentIndex);
                total++;
            }
            else {
                total = 0;
                currentIndex++;
                if (currentIndex >= upList.size()){
                    currentIndex = 0;
                }
            }
            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }
        return server;
    }

    @Override
    public Server choose(Object key){
        return choose(getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig){
    }
}

