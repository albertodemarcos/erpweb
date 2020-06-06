package com.informe.componentes;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.informe.componentes.interfaces.IProducerKafka;

@Component
public class ProductorKafka implements IProducerKafka {

	
	@Autowired
	private KafkaTemplate<String, String> productor;
	
	private static final String TOPIC = "";
	private static final String KEY = "";
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	

	@Override
	public void enviarDatosProductor() {
		
		logger.debug("Entramos en el metodo enviarDatosProductor() para enviar los datos procesados");
			
		ListenableFuture<SendResult<String, String>> resultadoEnvioDatos = this.productor.send(TOPIC, KEY, "");
			
		resultadoEnvioDatos.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				//Si el proceso ha sido enviado
				logger.debug("Los datos procesados han sido enviados correctamente a Kafka");
				
				logger.info("Datos enviados para: \n" + 
						"Topic: " + result.getProducerRecord().topic() + " \n" +
						"Partition: " + result.getProducerRecord().partition() + " \n" +
						"Timestamp: " + result.getProducerRecord().timestamp() + " \n" + 
						"Value: " + result.getProducerRecord().value() + " \n" );
			}

			@Override
			public void onFailure(Throwable ex) {
				//Si el proceso ha fallado
				logger.error("Error, el procesado de datos ha fallado. La causa es={}", ex.getMessage());
				
				ex.printStackTrace();
			}
		});
	}
	
	
	
	
	
	
}
