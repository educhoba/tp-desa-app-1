package application.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@RestController
@RequestMapping("/email")
public class EmailController {


	private EmailController() { }


	@PostMapping("/enviar/to/{to}/subject/{subject}/body/{body}")
	public void enviar(@PathVariable String to,@PathVariable String subject,@PathVariable String body) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp-mail.outlook.com");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("munibackend@hotmail.com", "uadewebapi2024");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("munibackend@hotmail.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(subject);
			message.setText(body);

			Transport.send(message);
			System.out.println("Email sent successfully.");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.err.println("Failed to send email: " + e.getMessage());
		}

	}
}
