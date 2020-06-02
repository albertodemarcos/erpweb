package com.erpweb.kafka.productores;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProductorPrueba {

	private Logger logger = LoggerFactory.getLogger(ProductorPrueba.class);
	
	private Properties properties;
	
	private String bootstrapServers = "127.0.0.1:9092"; //Localhost puerto 9092
	
	private KafkaProducer<Object, Object> producer; //Cambiar object por el objecto deseado (String, clase, etc)
	
	private ProducerRecord<Object, Object> record; //Cambiar object por el objecto deseado (String, clase, etc)
	
	public ProductorPrueba() {
		
	}
	
	//Inicializamos el properties del productor
	private void initProperties() {
		
		//Instanciamos las propiedades
		this.properties = new Properties();
		
		//Servidor
		this.properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		
		//Key serializada
		this.properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//Valor serializado
		this.properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
	}
	
	private void initProducer() {
		
		this.producer = new KafkaProducer<Object, Object>(this.properties);
	}
	
	//*Importante, por aqui es donde esta los datos que se van a enviar
	private void initProducerRecord() {
		
		String topic = "topic_first";
		String key = "key_1";
		String value = "value1";
		
		this.record = new ProducerRecord<Object, Object>(topic, key, value);//topic="", value=""
	}
	
	private void initSendRecord() {
		this.producer.send(this.record, new Callback() {
			@Override
			public void onCompletion(RecordMetadata metadata, Exception exception) {
				
				//Si sale correcto el envio de datos
				if(exception == null) {
					
					logger.info("Datos enviados para: \n" + 
								"Topic: " + metadata.topic() + " \n" +
								"Partition: " + metadata.partition() + " \n" +
								"Offset: " + metadata.offset() + " \n" +
								"Timestamp: " + metadata.timestamp() + " \n" );
					
				}else {
					
					logger.error("Error, no se ha podido certificar que han llegado correctamente los datos a kafka y la causa es={} ", exception.getMessage() );
					
					exception.printStackTrace();
				}
			}
		});
	}
	
	public void ejecutaFuncionProductor() {
		//Inicio 
		this.initProperties();
		//Productor
		this.initProducer();
		//Paso de mensajes del productor
		this.initProducerRecord();
		//Envio del mensaje del productor
		this.initSendRecord();
		//Flush
		this.producer.flush();
		//Close
		this.producer.close();
	}

	
	public static void main(String[] args) {
		
		
		ProductorPrueba productorPrueba = new ProductorPrueba();
		
		productorPrueba.ejecutaFuncionProductor();
		
		
	}
	
	
	
}
