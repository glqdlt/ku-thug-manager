package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@EnableAsync
@EnableScheduling
@Slf4j
@SpringBootApplication
public class KuThugManagerApplication implements CommandLineRunner {


    @Value("${thug.agent.url}")
    private String[] THUG_AGENTS;

    @Value("${url.csv.path}")
    private String URL_PATH;

    List<String[]> urlList;

    @Autowired
    SimpleThreadPools simpleThreadPools;

    @PostConstruct
    private void init() {

        try (Reader reader = Files.newBufferedReader(Paths.get(URL_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            urlList = csvReader.readAll();
        } catch (IOException e) {
            log.error(e.getMessage(), e);

        }
    }


    public static void main(String[] args) {
        SpringApplication.run(KuThugManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        batcher();
    }

    @Scheduled(fixedDelay = 1000)
    private void batcher(){
        int agentIndex = 0;
        for (String url[] : urlList) {
            if (agentIndex >= THUG_AGENTS.length) {
                agentIndex = 0;
            }
            simpleThreadPools.run(url[0], THUG_AGENTS[agentIndex]);
            agentIndex++;
        }
    }
}
