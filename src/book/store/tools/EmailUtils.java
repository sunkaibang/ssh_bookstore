package book.store.tools;

import java.io.File;
import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

//使用之前要导入activaction.jar和mail.jar 和在src下创建email_template.properties文件
//使用文件的时候，如果有多个文件用&隔开
//也可以使用{0}占位符的邮件内容，占位符从0开始，调用的时候传入参数就行

public class EmailUtils {
	
	public void sendEamil(String to,String... params){
		
		//加载邮件基本信息的配置文件
		Properties prop=new Properties();
		
		try {
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
			
			String host=prop.getProperty("host");
			String from=prop.getProperty("from");
			final String name=prop.getProperty("name");
			final String password=prop.getProperty("password");
			String subject=prop.getProperty("subject");
			String content=prop.getProperty("content");
			String file=prop.getProperty("file");
			
			//创建Session所需要的键值对
			Properties emailProp=System.getProperties();
			emailProp.setProperty("mail.host", host);
			emailProp.setProperty("mail.smtp.auth", "true");
			
			Authenticator auth=new Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication() {
					
					return new PasswordAuthentication(name,password);
				}
			};
			
			Session session=Session.getInstance(emailProp, auth);
			
			//创建邮件内容
			MimeMessage message=new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipients(RecipientType.TO, to);
			message.setSubject(subject);
			
			MimeMultipart list=new MimeMultipart();
			
			//文字内容
			MimeBodyPart textPart=new MimeBodyPart();
			content=MessageFormat.format(content, params);
			textPart.setContent(content, "text/html;charset=utf-8");
			list.addBodyPart(textPart);
			
			//附件内容
			if(file!=null && !file.trim().isEmpty()){
				if(file.contains("&")){
					String[] files=file.split("&");
					for(int i=0;i<files.length;i++){
						if(files[i]!=null && !files[i].trim().isEmpty()){
							MimeBodyPart part=new MimeBodyPart();
							part.attachFile(new File(files[i]));
							
							//处理一下文件的具体名称
							String[] filenames1=files[i].split("/");
							String filename1=filenames1[filenames1.length-1];
							part.setFileName(MimeUtility.encodeText(filename1));
							
							list.addBodyPart(part);
						}
					}
				}
				else{
					MimeBodyPart filePart=new MimeBodyPart();
					filePart.attachFile(new File(file));
					
					String[] filenames=file.split("/");
					String filename=filenames[filenames.length-1];
					filePart.setFileName(MimeUtility.encodeText(filename));
					
					list.addBodyPart(filePart);
				}
			}
			
			message.setContent(list);
			Transport.send(message);
			
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
	}
}
