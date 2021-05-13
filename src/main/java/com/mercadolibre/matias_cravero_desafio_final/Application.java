package com.mercadolibre.matias_cravero_desafio_final;

import com.mercadolibre.matias_cravero_desafio_final.config.SpringConfig;
import com.mercadolibre.matias_cravero_desafio_final.util.ScopeUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		ScopeUtils.calculateScopeSuffix();
		new SpringApplicationBuilder(SpringConfig.class).registerShutdownHook(true)
				.run(args);
	}
}
