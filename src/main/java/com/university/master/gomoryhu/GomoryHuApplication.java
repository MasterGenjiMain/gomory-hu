package com.university.master.gomoryhu;

import com.university.master.gomoryhu.Service.GomoryHuAlgorithm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GomoryHuApplication {

	public static void main(String[] args) {
		SpringApplication.run(GomoryHuApplication.class, args);
		GomoryHuAlgorithm gomoryHuAlgorithm = new GomoryHuAlgorithm();
		gomoryHuAlgorithm.showGraph();
	}

}
