package com.informe.kafka.configuracion.consumidor;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.informe.utiles.AccionRespuesta;

@Configuration
@EnableKafka
public class ConsumidorConfig {

	@Bean
    public ConsumerFactory<String, AccionRespuesta> consumerFactory() {
       
		Map<String, Object> propiedaes = new HashMap<>();
		
		propiedaes.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		propiedaes.put(ConsumerConfig.GROUP_ID_CONFIG, "group_json");
		propiedaes.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		propiedaes.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		

		return new DefaultKafkaConsumerFactory<>(propiedaes, new StringDeserializer(), new JsonDeserializer<>(AccionRespuesta.class));
    }
	
	@Bean
    public ConcurrentKafkaListenerContainerFactory<String, AccionRespuesta> consumerKafkaListenerFactory() {
		
        ConcurrentKafkaListenerContainerFactory<String, AccionRespuesta> factory = new ConcurrentKafkaListenerContainerFactory<>();
        
        factory.setConsumerFactory( consumerFactory() );
        
        return factory;
    }
	
	
}
