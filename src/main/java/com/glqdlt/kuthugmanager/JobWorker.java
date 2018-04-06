package com.glqdlt.kuthugmanager;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created By iw.jhun
 * On 2018-04-06 , 오전 11:16
 */
@Slf4j
public class JobWorker implements Callable<Integer> {

    private String url;
    private String thugAgent;

    public JobWorker(String url,String thugAgent) {
        this.url = url;
        this.thugAgent = thugAgent;
    }


    @Override
    public Integer call() {
//            RestTemplate restTemplate = new RestTemplate();
            log.info("[start] thug_agent : '{}' url : '{}'", thugAgent, url);
            Map<String, String> map = new HashMap<>();
            map.put("url", url);
//                    restTemplate.postForObject("http://127.0.0.1:15000/thug/remote/",map,String.class);
            log.info("[end] thug_agent : '{}' url : '{}'", thugAgent, url);
        return 1;
    }
}
