package com.glqdlt.kuthugmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By iw.jhun
 * On 2018-04-06 , 오전 11:16
 */
@Slf4j
public class JobWorker implements Runnable {

    private String url;
    private String thugAgent;

    public JobWorker(String url,String thugAgent) {
        super();
        this.url = url;
        this.thugAgent = thugAgent;
    }

    @Override
    public void run() {
//        RestTemplate restTemplate = new RestTemplate();
        log.info("[start] thug_agent : '{}' url : '{}'",thugAgent,url);
        Map<String, String> map = new HashMap<>();
        map.put("url", url);
//                    restTemplate.postForObject("http://127.0.0.1:15000/thug/remote/",map,String.class);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("[end] thug_agent : '{}' url : '{}'",thugAgent,url);
    }
}
