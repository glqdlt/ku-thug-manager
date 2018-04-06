package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ObjID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created By iw.jhun
 * On 2018-03-29 , 오후 4:53
 */

@Service
@Slf4j
public class SimpleThreadPools {

    @Value("${thug.agent.url}")
    private String[] THUG_AGENTS;


    @Bean
    public Executor asyncExecutor(){

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(THUG_AGENTS.length);
        threadPoolTaskExecutor.setThreadNamePrefix("ThugAgent-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;

    }

    @Async
    public void run(String url, String thugAgent){
        log.info("[start] thug_agent : '{}' url : '{}'", thugAgent, url);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
//                    restTemplate.postForObject("http://127.0.0.1:15000/thug/remote/",map,String.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("[end] thug_agent : '{}' url : '{}'", thugAgent, url);
    }
}
