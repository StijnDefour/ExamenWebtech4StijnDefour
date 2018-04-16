package edu.ap.spring.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ap.spring.model.InhaalExamen;
import edu.ap.spring.redis.RedisService;

@Controller
public class InhaalExamenController {
	
	private List<String> redisMessages = new ArrayList<String>();
	private RedisService service;
	
	@Autowired
	public void setRedisService(RedisService service) {
		this.service = service;
	}
	
	
	/*public String setNew(@RequestParam("student") String student, 
			   				@RequestParam("exam") String exam,
			   				@RequestParam("reason") String reason) {
		
			InhaalExamen examen = new InhaalExamen(student, exam, reason);
			String key = "inhaalexamens:"+examen.getStudent()+":"+examen.getExam()+":"+examen.getReason()+":"+examen.getDate();
			
			if (service.getKey(key) == null) {
				service.setKey(key, "1");
			}	
			
			return "result";
	}*/
	
	@RequestMapping(method = RequestMethod.POST, value="/new")
	public String setNew(@RequestBody InhaalExamen input) {
		
		String key = "inhaalexamens:"+input.getStudent()+":"+input.getExam()+":"+input.getReason()+":"+input.getDate();
		service.setKey(key, "1");
		
		return "result";
	}
	
	@RequestMapping("/list")
	public String setGrade(@RequestParam("student") String student) {

		
		String key = "inhaalexamens:"+student+":*";
		
		Set<String> inhaalexamens = service.keys(key);	
		
		return "result";
	}
	
	// Messaging
	public void onMessage(String message) {
		this.redisMessages.add(message);
	}
}
