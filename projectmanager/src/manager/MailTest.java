package manager;

import javax.mail.MessagingException;

public class MailTest {
	
	
	public static void main(String[] args) {
		
		try {
			MailHelper.sendMail("escadar1610@gmail.com", "java email Test", "test");
		} catch (MessagingException e) {
			 
			e.printStackTrace();
		}
	}

}
