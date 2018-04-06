package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

/**
 * Created By iw.jhun
 * On 2018-04-06 , 오후 3:58
 */
@Slf4j
@Component
public class ResourceManager {
    @Value("${thug.agent.url}")
    private String[] THUG_AGENTS;

    @Value("${url.csv.path}")
    private String URL_PATH;

    List<String[]> urlList;

    private int urlIndex;
    private int agentIndex;

    private int taskIndex;


    public int getAgentSize() {
        return this.THUG_AGENTS.length;
    }

    @PostConstruct
    private void init() {
        try (Reader reader = Files.newBufferedReader(Paths.get(URL_PATH));
             CSVReader csvReader = new CSVReader(reader)) {
            urlList = csvReader.readAll();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        this.urlIndex = 0;
        this.agentIndex = 0;

    }

    public synchronized JobTask taskBuild() {
        JobTask task = new JobTask();
        task.setAgent(this.THUG_AGENTS[this.agentIndex]);
        task.setUrl(this.urlList.get(this.urlIndex)[0]);
        upAgentIndex();
        upUrlIndex();
        upTaskIndex();
        return task;
    }

    public int getTaskIndex(){
        return this.taskIndex;
    }

    private void upAgentIndex() {
        this.agentIndex++;
        if (this.agentIndex >= this.THUG_AGENTS.length) {
            this.agentIndex = 0;
        }
    }

    private void upTaskIndex() {
        if (this.taskIndex < this.THUG_AGENTS.length) {
            this.taskIndex++;
        }
    }


    public synchronized void downTaskIndex() {
        if (this.taskIndex != 0) {
            this.taskIndex--;
        }
    }

    private void upUrlIndex() {
        this.urlIndex++;
        if (this.urlIndex >= urlList.size()) {
            this.urlIndex = 0;
        }
    }

}
