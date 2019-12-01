package com.mail.ipen;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

 class JavaMail {

	public static void sendMail(String recepient) throws MessagingException{
		System.out.println("Preparing to send email");
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String myAccountEmail ="Email address to send email";
		String password ="Put your password";
		
		Session session=Session.getInstance(properties, new Authenticator(){

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(myAccountEmail,password);
			}
			
		});
		Message message=prepareMessage(session,myAccountEmail, recepient);
		Transport.send(message);
		System.out.println("Message Send Successfully");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient) {
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Send Email From Java");
			//message.setText("Hello Everyone"); for simple message
			String htmlCode="<h1>We Love Java</h1></br><h1 style='color:red'>Hello</h1>";
			message.setContent(htmlCode, "text/html");//for displaying html content
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

public class JavaMailSend {

	public static void main(String[] args) {
		try {
			JavaMail.sendMail("The email where you want to send email");
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

}
