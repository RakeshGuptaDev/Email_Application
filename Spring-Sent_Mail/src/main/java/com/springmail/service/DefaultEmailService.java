package com.springmail.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.springmail.entity.MailDetails;
import com.springmail.repository.MailRepository;



@Service
public class DefaultEmailService implements EmailService {

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired
	private MailRepository mailRepository;

	@Value("${spring.mail.username}")
	private String sender;

//	@Override
//	public void addEmail(MailDetails mailDetails) {
//		mailDetails.setFrom(sender);
//		mailDetails.setSentDate(new Date());
//		mailRepository.save(mailDetails);
//	}

	@Override
	public List<MailDetails> getAllEmail() {

		return mailRepository.findAll();
	}

	@Override
	public void sendEmail(MailDetails mailDetails) {

		try {
			Date date = new Date();
			SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
			simpleMailMessage.setFrom(sender);
			mailDetails.setFroms(sender);
			simpleMailMessage.setTo(mailDetails.getRecipient());
			simpleMailMessage.setSentDate(date);
			mailDetails.setSentDate(date);
			simpleMailMessage.setSubject(mailDetails.getSubject());
			simpleMailMessage.setText(mailDetails.getMsgBody());
			System.out.println(simpleMailMessage+"-------------------------");
			System.out.println(mailDetails);
			mailRepository.save(mailDetails);
			javaMailSender.send(simpleMailMessage);
			System.out.println("Mail Sent Successfully...");

		} catch (Exception e) {
			System.out.println("Error while Sending Mail");
		}
	}

	@Override
	public void sendEmailWithAttachment(String toAddress, String subject, String message, String attachment)
			throws MessagingException, FileNotFoundException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom(sender);
		messageHelper.setTo(toAddress);
		messageHelper.setSubject(subject);
		messageHelper.setText(message);
		FileSystemResource file = new FileSystemResource(new File(attachment));
		boolean check = file.exists();
		System.out.println(check);
		messageHelper.addAttachment("Purchase Order", file);
		javaMailSender.send(mimeMessage);
	}

}