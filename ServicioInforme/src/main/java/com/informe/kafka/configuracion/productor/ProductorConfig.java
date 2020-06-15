package com.informe.kafka.configuracion.productor;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.informe.utiles.AccionRespuesta;

@Configuration
public class ProductorConfig {

	
	@Bean
    public ProducerFactory<String, AccionRespuesta> producerFactory() {
        
		Map<String, Object> propiedaes = new HashMap<>();

		propiedaes.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		propiedaes.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		propiedaes.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(propiedaes);
    }
	
	@Bean
    public KafkaTemplate<String, AccionRespuesta> kafkaTemplate() {
        
		return new KafkaTemplate<>(producerFactory());
    }
	
	
	
}
