package com.ecommerce.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpSession;

import com.ecommerce.user.dao.UsersDao;

public class SendForgotEmail { 
	public  void send(String from, String password, String to, String randomString) {
		// Get properties object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		// get Session
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		// compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Rest Password");
			message.setContent("<html>\r\n" + 
			 		"<head>\r\n" + 
			 		"<title></title>\r\n" + 
			 		"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
			 		"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" + 
			 		"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\r\n" + 
			 		"<style type=\"text/css\">\r\n" + 
			 		"	/* FONTS */\r\n" + 
			 		"    @media screen {\r\n" + 
			 		"		@font-face {\r\n" + 
			 		"		  font-family: 'Lato';\r\n" + 
			 		"		  font-style: normal;\r\n" + 
			 		"		  font-weight: 400;\r\n" + 
			 		"		  src: local('Lato Regular'), local('Lato-Regular'), url(https://fonts.gstatic.com/s/lato/v11/qIIYRU-oROkIk8vfvxw6QvesZW2xOQ-xsNqO47m55DA.woff) format('woff');\r\n" + 
			 		"		}\r\n" + 
			 		"		\r\n" + 
			 		"		@font-face {\r\n" + 
			 		"		  font-family: 'Lato';\r\n" + 
			 		"		  font-style: normal;\r\n" + 
			 		"		  font-weight: 700;\r\n" + 
			 		"		  src: local('Lato Bold'), local('Lato-Bold'), url(https://fonts.gstatic.com/s/lato/v11/qdgUG4U09HnJwhYI-uK18wLUuEpTyoUstqEm5AMlJo4.woff) format('woff');\r\n" + 
			 		"		}\r\n" + 
			 		"		\r\n" + 
			 		"		@font-face {\r\n" + 
			 		"		  font-family: 'Lato';\r\n" + 
			 		"		  font-style: italic;\r\n" + 
			 		"		  font-weight: 400;\r\n" + 
			 		"		  src: local('Lato Italic'), local('Lato-Italic'), url(https://fonts.gstatic.com/s/lato/v11/RYyZNoeFgb0l7W3Vu1aSWOvvDin1pK8aKteLpeZ5c0A.woff) format('woff');\r\n" + 
			 		"		}\r\n" + 
			 		"		\r\n" + 
			 		"		@font-face {\r\n" + 
			 		"		  font-family: 'Lato';\r\n" + 
			 		"		  font-style: italic;\r\n" + 
			 		"		  font-weight: 700;\r\n" + 
			 		"		  src: local('Lato Bold Italic'), local('Lato-BoldItalic'), url(https://fonts.gstatic.com/s/lato/v11/HkF_qI1x_noxlxhrhMQYELO3LdcAZYWl9Si6vvxL-qU.woff) format('woff');\r\n" + 
			 		"		}\r\n" + 
			 		"    }\r\n" + 
			 		"    \r\n" + 
			 		"    /* CLIENT-SPECIFIC STYLES */\r\n" + 
			 		"    body, table, td, a { -webkit-text-size-adjust: 100%; -ms-text-size-adjust: 100%; }\r\n" + 
			 		"    table, td { mso-table-lspace: 0pt; mso-table-rspace: 0pt; }\r\n" + 
			 		"    img { -ms-interpolation-mode: bicubic; }\r\n" + 
			 		"\r\n" + 
			 		"    /* RESET STYLES */\r\n" + 
			 		"    img { border: 0; height: auto; line-height: 100%; outline: none; text-decoration: none; }\r\n" + 
			 		"    table { border-collapse: collapse !important; }\r\n" + 
			 		"    body { height: 100% !important; margin: 0 !important; padding: 0 !important; width: 100% !important; }\r\n" + 
			 		"\r\n" + 
			 		"    /* iOS BLUE LINKS */\r\n" + 
			 		"    a[x-apple-data-detectors] {\r\n" + 
			 		"        color: inherit !important;\r\n" + 
			 		"        text-decoration: none !important;\r\n" + 
			 		"        font-size: inherit !important;\r\n" + 
			 		"        font-family: inherit !important;\r\n" + 
			 		"        font-weight: inherit !important;\r\n" + 
			 		"        line-height: inherit !important;\r\n" + 
			 		"    }\r\n" + 
			 		"\r\n" + 
			 		"    /* ANDROID CENTER FIX */\r\n" + 
			 		"    div[style*=\"margin: 16px 0;\"] { margin: 0 !important; }\r\n" + 
			 		"</style>\r\n" + 
			 		"</head>\r\n" + 
			 		"<body style=\"background-color: #f4f4f4; margin: 0 !important; padding: 0 !important;\">\r\n" + 
			 		"\r\n" + 
			 		"<!-- HIDDEN PREHEADER TEXT -->\r\n" + 
			 		"<div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: 'Lato', Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\r\n" + 
			 		"    Looks like you tried signing in a few too many times. Let's see if we can get you back into your account.\r\n" + 
			 		"</div>\r\n" + 
			 		"\r\n" + 
			 		"<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n" + 
			 		"    <!-- LOGO -->\r\n" + 
			 		"    " + 
			 		"    <!-- HERO -->\r\n" + 
			 		"    <tr>\r\n" + 
			 		"        <td bgcolor=\"#7c72dc\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\r\n" + 
			 		"            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\r\n" + 
			 		"                <tr>\r\n" + 
			 		"                    <td bgcolor=\"#ffffff\" align=\"center\" valign=\"top\" style=\"padding: 40px 20px 20px 20px; border-radius: 4px 4px 0px 0px; color: #111111; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; letter-spacing: 4px; line-height: 48px;\">\r\n" + 
			 		"                      <h1 style=\"font-size: 32px; font-weight: 400; margin: 0;\">Reset your Password</h1>\r\n" + 
			 		"                    </td>\r\n" + 
			 		"                </tr>\r\n" + 
			 		"            </table>\r\n" + 
			 		"        </td>\r\n" + 
			 		"    </tr>\r\n" + 
			 		"    <!-- COPY BLOCK -->\r\n" + 
			 		"    <tr>\r\n" + 
			 		"        <td bgcolor=\"#f4f4f4\" align=\"center\" style=\"padding: 0px 10px 0px 10px;\">\r\n" + 
			 		"            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"480\" >\r\n" + 
			 		"              <!-- COPY -->\r\n" + 
			 		"              <tr>\r\n" + 
			 		"                <td bgcolor=\"#ffffff\" align=\"left\" style=\"padding: 20px 30px 40px 30px; color: #666666; font-family: 'Lato', Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 25px;\" >\r\n" + 
			 		"                  <p style=\"margin: 0;\">Resetting your password is easy. Just press the button below and follow the instructions. We'll have you up and running in no time. </p>\r\n" + 
			 		"                </td>\r\n" + 
			 		"              </tr>\r\n" + 
			 		"              <!-- BULLETPROOF BUTTON -->\r\n" + 
			 		"              <tr>\r\n" + 
			 		"                <td bgcolor=\"#ffffff\" align=\"left\">\r\n" + 
			 		"                  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			 		"                    <tr>\r\n" + 
			 		"                      <td bgcolor=\"#ffffff\" align=\"center\" style=\"padding: 20px 30px 60px 30px;\">\r\n" + 
			 		"                        <table border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\r\n" + 
			 		"                          <tr>\r\n" + 
			 		"                              <td align=\"center\" style=\"border-radius: 3px;\" bgcolor=\"#7c72dc\"><h1>"+randomString+"</h1></td>\r\n" + 
			 		"                          </tr>\r\n" + 
			 		"                        </table>\r\n" + 
			 		"                      </td>\r\n" + 
			 		"                    </tr>\r\n" + 
			 		"                  </table>\r\n" + 
			 		"                </td>\r\n" + 
			 		"              </tr>\r\n" + 
			 		"            </table>\r\n" + 
			 		"        </td>\r\n" + 
			 		"    </tr>\r\n" + 
			 		"    <!-- COPY CALLOUT -->\r\n" + 
			 		"    <tr>\r\n" + 
			 		"        " + 
			 		"    </tr>\r\n" + 
			 		"    <!-- SUPPORT CALLOUT -->\r\n" + 
			 		"    <!-- FOOTER -->\r\n" + 
			 		"    "+"</table>\r\n" + 
			 		"\r\n" + 
			 		"</body>\r\n" + 
			 		"</html>\r\n" + 
			 		"","text/html");

			// send message
			Transport.send(message);
			System.out.println("sent email successfully");
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
    
	}
}
