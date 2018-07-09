package liuxiaoran.tools;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;

public class sendCode {
	// 发件人的 邮箱 和 密码（替换为自己的邮箱和密码）
    // PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）, 
    //     对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。
	private static String myEmailAccount = "512748799@qq.com";
 private static String myEmailPassword = "xuicbonejgyccahj";
//网易163邮箱的 SMTP 服务器地址为: smtp.163.com
private  static String myEmailSMTPHost = "smtp.qq.com";
private Properties props = new Properties(); 
 public sendCode(){
	 MailSSLSocketFactory sf=null;
	try {
		sf = new MailSSLSocketFactory();
	} catch (GeneralSecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	sf.setTrustAllHosts(true);                    // 参数配置
	    props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
	    props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
	    props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.socketFactory", sf);
 } 
 public void send(String receiveMail,String username,int code) throws UnsupportedEncodingException, MessagingException {
	  Session session = Session.getInstance(props);
	    session.setDebug(true);            
	    MimeMessage message = new MimeMessage(session);
	    // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
//	    message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("15067148656@163.com", "liuxiaoran", "UTF-8"));
	    message.setFrom(new InternetAddress(myEmailAccount, "刘萧然", "UTF-8"));
	    // 3. To: 收件人（可以增加多个收件人、抄送、密送）
	    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "username", "UTF-8"));

	    // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
	    message.setSubject("您的验证码", "UTF-8");

	    // 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
	    message.setContent("用户:"+username+"<br />您的验证码为:"+"<span color='red'>"+code+"</span>", "text/html;charset=UTF-8");
	    // 6. 设置发件时间
	    message.setSentDate(new Date());
	    // 7. 保存设置
	    message.saveChanges();

	    // 4. 根据 Session 获取邮件传输对象
	    Transport transport = session.getTransport();
	    
	 // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
	    transport.connect(myEmailAccount, myEmailPassword);
	    
	 // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
	    transport.sendMessage(message, message.getAllRecipients());
	    /*Transport.send(message);*/
	 // 7. 关闭连接
	    transport.close();
}
}
