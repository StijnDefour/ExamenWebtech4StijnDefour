package edu.ap.spring;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import edu.ap.spring.controller.InhaalExamenController;
import edu.ap.spring.redis.RedisService;

@SpringBootApplication
public class InhaalExamenApplication {
	private RedisService service;
	private String CHANNEL = "edu:ap:spring";
	private String KEY = "edu:ap:spring:test";
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
	}
	
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
											MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new ChannelTopic(CHANNEL));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(InhaalExamenController controller) {
		return new MessageListenerAdapter(controller, "onMessage");
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return (args) -> {
			
		};
    }
	
	public static void main(String[] args) {
		SpringApplication.run(InhaalExamenApplication.class, args);
	}
}
