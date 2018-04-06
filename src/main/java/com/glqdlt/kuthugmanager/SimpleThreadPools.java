package com.glqdlt.kuthugmanager;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created By iw.jhun
 * On 2018-03-29 , 오후 4:53
 */

@Component
@Slf4j
public class SimpleThreadPools {

    @Value("${thug.agent.url}")
    private String[] THUG_AGENTS;

    public void runner() {


        // TODO csv 파일을 읽어와서 콜렉션으로 return 하는 기능 개발 필요
        List<String> list = new ArrayList<>();
//        list.add("http://www.google.co.kr");
//        list.add("http://www.daum.net");
//        list.add("http://www.naver.com");
//        list.add("http://www.nate.com");

        for(int i =0; i<1000; i++){
            list.add("http://www.asdasd.00"+i);
        }



        ExecutorService executor = Executors.newFixedThreadPool(THUG_AGENTS.length);

        int agentNumb = 0;

        while (true) {

            if(agentNumb >= THUG_AGENTS.length){
                agentNumb = 0;
            }

            for (String url : list) {
                executor.execute(new JobWorker(url,THUG_AGENTS[agentNumb]));
            }

            agentNumb++;
        }
    }

}

