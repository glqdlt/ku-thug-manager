package com.glqdlt.kuthugmanager;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class SimpleThreadPools {
    public void runner() {


        List<String> list = new ArrayList<>();
        list.add("http://www.google.co.kr");
        list.add("http://www.daum.net");
        list.add("http://www.naver.com");
        list.add("http://www.nate.com");


        ExecutorService executor = Executors.newFixedThreadPool(2);

        while (true) {

            for (String url : list) {
                executor.execute(() -> {
                    log.info("start :" + url);
                    RestTemplate restTemplate = new RestTemplate();
                    Map<String, String> map = new HashMap<>();
                    map.put("url", url);
//                    restTemplate.postForObject("http://127.0.0.1:15000/thug/remote/",map,String.class);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        log.error(e.getMessage(), e);
                    }
                    log.info("end :" + url);
                });
            }
        }
//        executor.shutdown();

    }

}

