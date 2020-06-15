package com.erpweb.kafka.consumidores;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erpweb.kafka.productores.ProductorPrueba;

public class ConsumidorPrueba {

	private Logger logger = LoggerFactory.getLogger(ProductorPrueba.class);
	
	private Properties properties;
	
	private String bootstrapServers = "127.0.0.1:9092"; //Localhost puerto 9092
	
	private String groupId = "my_group";
	
	private Long duracionRecord = 100L;
	
	private String topic = "topic_first";
	
	private KafkaConsumer<Object, Object> consumer; //Cambiar object por el objecto deseado (String, clase, etc)
	
	private ConsumerRecords<Object, Object> records; //Cambiar object por el objecto deseado (String, clase, etc)
	
	public ConsumidorPrueba() {
		
	}
	
	//Inicializamos el properties del productor
	private void initProperties() {
			
		//Instanciamos las propiedades
		this.properties = new Properties();
		
		//Servidor
		this.properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		
		//Key serializada
		this.properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//Valor serializado
		this.properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		//Grupo consumidor
		this.properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		
		//Offset ==> earliest/lastest/none
		this.properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earlist");
	}
	
	private void initConsumer() {
		
		this.consumer = new KafkaConsumer<Object, Object>(this.properties);
	}
	
	//*Importante, por aqui es donde estan los datos que se van a recibir
	private void initConsumerSubcribe() {
		
		//Para una sola subcripcion
		//this.consumer.subscribe( Collections.singleton( topic ) );
		
		//Para varias subcripciones
		//this.consumer.subscribe(Arrays.asList("topic_1","topic_2")   );
		
		//Ponerlo asi siempre
		this.consumer.subscribe( Arrays.asList( topic ) );
	}
	
	//Recibimos la informacion del productor
	private void initReceiveRecord() {
		
		while(true) {
			
			this.records = consumer.poll(Duration.ofMillis( duracionRecord ) );
			
			for( ConsumerRecord<Object, Object> record : records ) {
				
				logger.info("Datos recibidos: \n" + 
						"Topic: " + record.topic() + " \n" +
						"Key: " + record.key() + " \n" +
						"Value: " + record.value() + " \n" +
						"Partition: " + record.partition() + " \n" +
						"Offset: " + record.offset() + " \n" +
						"Timestamp: " + record.timestamp() + " \n" );
			}
		}
	}
	
	public void ejecutaFuncionConsumidor() {
		//Inicio 
		this.initProperties();
		//Productor
		this.initConsumer();
		//Paso de mensajes del productor
		this.initConsumerSubcribe();
		//Envio del mensaje del productor
		this.initReceiveRecord();
		//Flush
		//this.producer.flush();
		//Close
		//this.producer.close();
	}
	
	
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub

	}*/

}
