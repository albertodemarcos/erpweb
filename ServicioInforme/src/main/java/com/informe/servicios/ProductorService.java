package com.informe.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class ProductorService {
	
	private static final String TOPIC = "topic_1";
	
	private static final String KEY = "key_1";
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void enviarMensaje(String mensaje) {
		
		this.kafkaTemplate.send(TOPIC, KEY, mensaje);
	}
	
	
	

}
