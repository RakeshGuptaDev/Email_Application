package com.springmail.controller;

import java.io.FileNotFoundException;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springmail.entity.MailDetails;
import com.springmail.service.EmailService;

@RestController
@RequestMapping("/email")
public class EmailController {

	private static final Logger LOG = LoggerFactory.getLogger(EmailController.class);

	@Autowired
	private EmailService emailService;

//	@PostMapping("/addinfo")
//	public ResponseEntity<String> addMail(@RequestBody MailDetails mailDetails) {
//		emailService.addEmail(mailDetails);
//		return new ResponseEntity<>("Email Data added successfully to send email", HttpStatus.CREATED);
//
//	}

	@PostMapping("/sendmail")
	public @ResponseBody ResponseEntity<String> sendSimpleEmail(@RequestBody MailDetails mailDetails) throws Exception {

		try {
			emailService.sendEmail(mailDetails);
		} catch (MailException mailException) {
			LOG.error("Error while sending out email..{}", mailException.getStackTrace());

			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
	}

	@GetMapping("/getinfo")
	public ResponseEntity<List<MailDetails>> addMail() {
		return new ResponseEntity<>(emailService.getAllEmail(), HttpStatus.CREATED);

	}

	@GetMapping(value = "/simple-order-email/{user-email}")
	public @ResponseBody ResponseEntity<String> sendEmailAttachment(@PathVariable("user-email") String email) {

		try {
			emailService.sendEmailWithAttachment(email, "Order Confirmation", "Thanks for your recent order",
					"/home/admin/Documents/workspace-spring-tool-suite-4-4.14.1.RELEASE/Spring-Client-Mail/src/main/resources/purchase_order.pdf");
		} catch (MessagingException | FileNotFoundException mailException) {
			LOG.error("Error while sending out email..{}", mailException.getStackTrace());
			LOG.error("Error while sending out email..{}", mailException.fillInStackTrace());
			return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("Please check your inbox for order confirmation", HttpStatus.OK);
	}

}