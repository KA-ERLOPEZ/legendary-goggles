package com.sistema.pizzeria.elpirata.api.web.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistema.pizzeria.elpirata.api.core.services.EmailService;

public class EmailController {
	
	private EmailService emailService;


	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/sendSimpleEmail")
	public ResponseEntity<String> sendSimpleEmail(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text) {
		emailService.sendSimpleMessage(to, subject, text);
		return ResponseEntity.ok("Email enviado correctamente");
	}

	@PostMapping("/sendHtmlEmail")
	public ResponseEntity<String> sendHtmlEmail(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text) {
		emailService.sendHtmlMessage(to, subject, text);
		return ResponseEntity.ok("Email enviado correctamente");
	}

	@PostMapping("/sendEmailWithAttachment")
	public ResponseEntity<String> sendEmailWithAttachment(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text, @RequestParam String pathToAttachment) {
		emailService.sendSimpleMessageWithAttachment(to, subject, text, pathToAttachment);
		return ResponseEntity.ok("Email enviado correctamente");
	}

	@PostMapping("/sendEmailWithInlineResource")
	public ResponseEntity<String> sendEmailWithInlineResource(@RequestParam String to, @RequestParam String subject,
			@RequestParam String text, @RequestParam String pathToInlineResource, @RequestParam String cid) {
		emailService.sendSimpleMessageWithInlineResource(to, subject, text, pathToInlineResource, cid);
		return ResponseEntity.ok("Email enviado correctamente");
	}

}
