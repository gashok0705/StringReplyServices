package com.beta.replyservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.beta.replyservice.service.ReplyService;

@RestController
public class ReplyController {

	@Autowired
	private ReplyService service;
	
	@GetMapping("/reply")
	public ReplyMessage replying() {
		return new ReplyMessage("Message is empty");
	}

	@GetMapping("/reply/{message}")
	public ReplyMessage replying(@PathVariable String message) {
		String result = this.service.adoptToRule(message);
		return new ReplyMessage(result);
	}
}