package rs.ac.uns.ftn.backend.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.backend.model.Administrator;
import rs.ac.uns.ftn.backend.model.User;
import rs.ac.uns.ftn.backend.model.enumeration.Role;


@Service
public class EmailService {
	
	@Autowired
    private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final String EMAIL_SENDER = "spring.mail.username";
	
	private SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public void sendRegistrationConfirmationEmail(User user) throws MailException, InterruptedException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setFrom(environment.getProperty(EMAIL_SENDER));
		mail.setSubject("Verification mail for user ");
		String encodedId = String.valueOf(user.getId());
		encodedId = Base64.getEncoder().encodeToString(encodedId.getBytes());
		mail.setText("Verification url is: http://localhost:8080/auth/confirmRegistration/"+encodedId+". Click link to verify account!");
		javaMailSender.send(mail);
	}
	
	// warn administrators about suspicious fraud case
	public void warnAdministratorsAboutSuspiciousFraudCase(Long userId) throws MailException, InterruptedException {
		// send mail to all administrators
		List<User> users = userService.findAll();
		for (User user : users) {
			if (user instanceof Administrator) {
				SimpleMailMessage mail = new SimpleMailMessage();
				mail.setTo(user.getEmail());
				mail.setFrom(environment.getProperty(EMAIL_SENDER));
				mail.setSubject("WARNING: Suspicious fraud case.");
				mail.setText("WARNING: Suspicious fraud case. User with id: " + userId);
				javaMailSender.send(mail);
				System.out.println("Warn email sent.");
			}
		}
		

	}
	
	/*
	 * public void sendEventReminder(User user, Event event) throws MailException,
	 * InterruptedException{ SimpleMailMessage mail = new SimpleMailMessage();
	 * mail.setTo(user.getEmail());
	 * mail.setFrom(environment.getProperty(EMAIL_SENDER));
	 * mail.setSubject("Reminder for event "+ event.getName());
	 * 
	 * mail.setText("Dear " + user.getFirstName() +
	 * "!\n\n We remind you that tomorrow, on " + sf.format(event.getStartDate()) +
	 * " is the event " + event.getName()+
	 * ", on which you bought earlier tickets at our website.\n\n"
	 * +"We wish you a good time! "); javaMailSender.send(mail); }
	 * 
	 * public void sendBuyingReminder(User user, Event event) { SimpleMailMessage
	 * mail = new SimpleMailMessage(); mail.setTo(user.getEmail());
	 * mail.setFrom(environment.getProperty(EMAIL_SENDER));
	 * mail.setSubject("Reminder for buying the tickets for "+ event.getName());
	 * 
	 * mail.setText("Dear " + user.getFirstName() +
	 * "!\n\n We remind you that if you don't buy your" +
	 * " tickets by tomorrow for event " + event.getName()+
	 * " your reservation will be automatically deleted.");
	 * javaMailSender.send(mail); }
	 */
	
}
