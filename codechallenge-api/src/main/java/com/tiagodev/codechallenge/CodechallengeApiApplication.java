package com.tiagodev.codechallenge;

import com.tiagodev.codechallenge.core.usecase.SalvarContasBancariasUseCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodechallengeApiApplication implements ApplicationRunner {

	@Autowired
	private SalvarContasBancariasUseCase salvarContasBancariasUseCase;

	public static void main(String[] args) {
		SpringApplication.run(CodechallengeApiApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		this.salvarContasBancariasUseCase.executar();
	}

}