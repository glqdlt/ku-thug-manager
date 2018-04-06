package com.glqdlt.kuthugmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KuThugManagerApplication implements CommandLineRunner {

	@Autowired
	SimpleThreadPools simpleThreadPools;


	public static void main(String[] args) {
		SpringApplication.run(KuThugManagerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		simpleThreadPools.runner();
	}
}
