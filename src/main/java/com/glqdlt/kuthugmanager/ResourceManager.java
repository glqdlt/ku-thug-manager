package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created By iw.jhun
 * On 2018-04-06 , 오후 3:58
 */
@Slf4j
@Component
public class ResourceManager {
    @Value("${agent.config.path}")
    private String THUG_PATH;

    @Value("${url.config.path}")
    private String URL_PATH;

    List<String[]> urlList;
    private List<String[]> agentList;

    private int urlIndex;
    private int agentIndex;

    private int taskIndex;


    public int getAgentSize() {
        return this.agentList.size();
    }

    @PostConstruct
    private void init() {
        try (
                CSVReader urlReader = new CSVReader(Files.newBufferedReader(Paths.get(URL_PATH)));
                CSVReader agentReader = new CSVReader(Files.newBufferedReader(Paths.get(THUG_PATH)))
        ) {
            urlList = urlReader.readAll();
            agentList = agentReader.readAll();

        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        this.urlIndex = 0;
        this.agentIndex = 0;

    }

    public synchronized JobTask taskBuild() {
        JobTask task = new JobTask();
        task.setAgent(this.agentList.get(this.agentIndex)[0]);
        task.setUrl(this.urlList.get(this.urlIndex)[0]);
        upAgentIndex();
        upUrlIndex();
        upTaskIndex();
        return task;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    private void upAgentIndex() {
        this.agentIndex++;
        if (this.agentIndex >= this.agentList.size()) {
            this.agentIndex = 0;
        }
    }

    private void upTaskIndex() {
        if (this.taskIndex < this.agentList.size()) {
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
