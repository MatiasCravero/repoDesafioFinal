package com.mercadolibre.matias_cravero_desafio_final.beans;

import com.mercadolibre.matias_cravero_desafio_final.dto.SampleDTO;
import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class RandomSampleBean {
	private Random random = new Random();

	public SampleDTO random() {
		return new SampleDTO(random.nextInt(Integer.MAX_VALUE));
	}
}

