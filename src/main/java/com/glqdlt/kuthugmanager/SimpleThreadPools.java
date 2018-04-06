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
import java.util.List;
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

    @Value("${url.csv.path}")
    private String URL_PATH;

    List<String[]> urlList;

    @PostConstruct
    private void init() throws IOException {

        Reader reader
                = Files.newBufferedReader(Paths.get(URL_PATH));
        CSVReader csvReader = new CSVReader(reader);
        urlList = csvReader.readAll();
    }

    public void runner() {


        ExecutorService executor = Executors.newFixedThreadPool(THUG_AGENTS.length);

        int agentIndex = 0;
        int urlIndex = 0;

        while (true) {

            if (agentIndex >= THUG_AGENTS.length) {
                agentIndex = 0;
            }
            if (urlIndex >= urlList.size()) {
                urlIndex = 0;
            }

            executor.execute(new JobWorker(urlList.get(urlIndex)[0], THUG_AGENTS[agentIndex]));

            agentIndex++;
            urlIndex++;
        }
    }

}

