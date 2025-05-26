package com.email.service;

import java.io.File;
import javax.mail.Authenticator;              
import javax.mail.PasswordAuthentication;    

import java.util.Properties;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.Session; 
import javax.mail.Message;     
import javax.mail.Transport;  


import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public Boolean sendEmail(String subject, String message, String to) {
		Boolean f= false;
		String from = "Sharmachitransh626@gmail.com";
		
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		System.out.println("Properties: " + properties);

		// host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", 465);
		properties.put("mail.smtp.ssl.enable", true);
		properties.put("mail.smtp.auth", true);

		// to get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("sharmachitransh626@gmail.com", "aonvggyzynlofsdk");

			}

		});	

		session.setDebug(true);

		// compose the message [text, multi media];
		MimeMessage m = new MimeMessage(session);
		try {
			// from email
			m.setFrom();
			// adding recipitent
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

			// adding subject
			m.setSubject(subject);

			// adding text to message
			// m.setText(message);

			// file path
			String path = "C:\\Users\\chitransh.sharma\\Documents\\Rocket.pdf";

			MimeMultipart mineMultipart = new MimeMultipart();
			// text
			// file

			MimeBodyPart textMime = new MimeBodyPart();

			MimeBodyPart fileMime = new MimeBodyPart();

			try {
				textMime.setText(message);
				File file = new File(path);
				fileMime.attachFile(file);

				mineMultipart.addBodyPart(textMime);
				mineMultipart.addBodyPart(fileMime);

			} catch (Exception e) {
				e.printStackTrace();
			}
			m.setContent(mineMultipart);

			// send
			Transport.send(m);

			System.out.println("send succesfully..........");
			f = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
