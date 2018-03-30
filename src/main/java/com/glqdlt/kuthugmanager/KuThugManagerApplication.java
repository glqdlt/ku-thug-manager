package com.glqdlt.kuthugmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KuThugManagerApplication {



	public static void main(String[] args) {
		SpringApplication.run(KuThugManagerApplication.class, args);
		SimpleThreadPools simpleThreadPools = new SimpleThreadPools();
		simpleThreadPools.runner();

	}

}
