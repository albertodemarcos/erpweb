package com.informe.componentes;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.informe.componentes.interfaces.IConsumerKafka;

@Component
public class ConsumidorKafka implements IConsumerKafka {

	
	private static final String TOPICS = "";
	private static final String KEY = "";
	
	
	@KafkaListener (topics = TOPICS)
	public void recibirDatos() {
		// TODO Auto-generated method stub
		
		
		
		
		
		
		
	}
	
}
