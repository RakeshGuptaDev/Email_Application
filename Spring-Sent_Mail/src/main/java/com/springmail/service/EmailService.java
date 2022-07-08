package com.springmail.service;

import java.io.FileNotFoundException;
import java.util.List;

import javax.mail.MessagingException;

import com.springmail.entity.MailDetails;

public interface EmailService {
	
	public List<MailDetails> getAllEmail();
	// void addEmail(MailDetails mailDetails);
	 void sendEmail(MailDetails mailDetails)throws Exception;
     void sendEmailWithAttachment(final String toAddress, final String subject, final String message, final String attachment) throws MessagingException, FileNotFoundException;


}
