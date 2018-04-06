package com.glqdlt.kuthugmanager;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.applet.Main;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created By iw.jhun
 * On 2018-04-06 , 오전 11:44
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCsvRead {
    @Value("${url.csv.path}")
    private String URL_PATH;


//    @Test
    public void makeFile() throws IOException {
        String path = "C:\\Users\\iw.jhun\\Documents\\workspace-idea\\ku-thug-manager\\src\\main\\resources";
        for(int i=0; i<9999;i++){
            String text = "http://"+i+System.lineSeparator();
            Files.write(Paths.get(path,"test.csv"),text.getBytes(), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
        }
    }

    @Test
    public void test() {
        try {
            Reader reader
                    = Files.newBufferedReader(Paths.get(URL_PATH));
            CSVReader csvReader = new CSVReader(reader);
            csvReader.readAll().forEach(x -> log.info(x[0]));
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}