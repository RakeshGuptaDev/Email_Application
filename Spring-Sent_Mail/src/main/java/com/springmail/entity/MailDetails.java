package com.springmail.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class MailDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int mailId;

	@Nullable
	private String subject;

	@Nullable
	private String msgBody;

	@Nullable
	private String froms;

	// Hibernate: create table mail_details (mail_id integer not null
	// auto_increment, from varchar(255), msg_body varchar(255), recipient tinyblob,
	// sent_date datetime(6), subject varchar(255), primary key (mail_id))
	// engine=InnoDB

//	@Nullable
//	private String replyTo;

	@Nullable
	private String[] recipient;

//	@Nullable
//	private String[] cc;

//	@Nullable
//	private String[] bcc;

	@Nullable
	private Date sentDate;

	public MailDetails() {
		super();
	}

	public MailDetails(int mailId, String froms, String[] recipient, Date sentDate, String subject, String msgBody) {
		super();
		this.mailId = mailId;
		this.froms = froms;
		this.recipient = recipient;
		this.sentDate = sentDate;
		this.subject = subject;
		this.msgBody = msgBody;
	}

	public int getMailId() {
		return mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

	

	public String getFroms() {
		return froms;
	}

	public void setFroms(String froms) {
		this.froms = froms;
	}

	public String[] getRecipient() {
		return recipient;
	}

	public void setRecipient(String[] recipient) {
		this.recipient = recipient;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

}
