package com.glqdlt.kuthugmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

/**
 * Created By iw.jhun
 * On 2018-03-29 , 오후 4:53
 */

@Service
@Slf4j
public class RunnerPool {

    @Autowired
    ResourceManager resourceManager;


    @Bean
    public Executor asyncExecutor() {

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(resourceManager.getAgentSize());
        threadPoolTaskExecutor.setThreadNamePrefix("ThugAgent-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }


    @Async
    public void runner(int i, JobTask task) throws InterruptedException {
        String agent = task.getAgent();
        String url = task.getUrl();
        log.info("[start-{}] thug_agent : '{}' url : '{}'", i, agent, url);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
//                    restTemplate.postForObject("http://127.0.0.1:15000/thug/remote/",map,String.class);
        Thread.sleep(100);
        log.info("[end-{}] thug_agent : '{}' url : '{}'", i, agent, url);
        resourceManager.downTaskIndex();
    }
}
