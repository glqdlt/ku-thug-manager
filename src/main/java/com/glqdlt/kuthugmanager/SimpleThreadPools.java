package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.server.ObjID;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created By iw.jhun
 * On 2018-03-29 , 오후 4:53
 */

@Component
@Slf4j
public class SimpleThreadPools {

    @Value("${thug.agent.url}")
    private String[] THUG_AGENTS;

    @Value("${url.csv.path}")
    private String URL_PATH;

    List<String[]> urlList;

    @PostConstruct
    private void init() {

        try (Reader reader = Files.newBufferedReader(Paths.get(URL_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            urlList = csvReader.readAll();
        } catch (IOException e) {
            log.error(e.getMessage(), e);

        }
    }

    public void runner() {
        ExecutorService executor = Executors.newFixedThreadPool(THUG_AGENTS.length);
        for(int i=0; i<999;i++) {
            int agentIndex = 0;
            for (String url[] : urlList) {
                if (agentIndex >= THUG_AGENTS.length) {
                    agentIndex = 0;
                }
                executor.submit(new JobWorker(url[0], THUG_AGENTS[agentIndex]));
                agentIndex++;
            }
        }

    }
}
