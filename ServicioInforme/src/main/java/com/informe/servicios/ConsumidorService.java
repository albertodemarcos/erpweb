package com.informe.servicios;

import org.springframework.kafka.annotation.KafkaListener;

public class ConsumidorService {

	
	
	@KafkaListener(topics = {"topic_1","topic_2"} , groupId = "group_id" )
	public void recibirMensajes(String mensaje) {
		
		//Recibimos los mensajes
		System.out.println("Mensaje: " + mensaje);
		
	}
	
	
	
	
	
}
