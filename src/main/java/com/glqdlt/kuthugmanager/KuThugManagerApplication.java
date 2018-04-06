package com.glqdlt.kuthugmanager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Slf4j
@SpringBootApplication
public class KuThugManagerApplication implements CommandLineRunner {


    @Autowired
    RunnerPool runnerPool;

    @Autowired
    ResourceManager resourceManager;

    public static void main(String[] args) {
        SpringApplication.run(KuThugManagerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        int i = 0;
        while (true) {
            if (resourceManager.getTaskIndex() < resourceManager.getAgentSize()) {
                i++;
                JobTask task = resourceManager.taskBuild();
                log.info("Start-{} job agent : {} , url : {}", i, task.getAgent(), task.getUrl());
                runnerPool.runner(i, task);
            }
        }
    }
}
