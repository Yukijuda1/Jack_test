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
	// �����˵� ���� �� ���루�滻Ϊ�Լ�����������룩
    // PS: ĳЩ���������Ϊ���������䱾������İ�ȫ�ԣ��� SMTP �ͻ��������˶������루�е������Ϊ����Ȩ�롱��, 
    //     ���ڿ����˶������������, ����������������ʹ������������루��Ȩ�룩��
	private static String myEmailAccount = "512748799@qq.com";
 private static String myEmailPassword = "xuicbonejgyccahj";
//����163����� SMTP ��������ַΪ: smtp.163.com
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
	sf.setTrustAllHosts(true);                    // ��������
	    props.setProperty("mail.transport.protocol", "smtp");   // ʹ�õ�Э�飨JavaMail�淶Ҫ��
	    props.setProperty("mail.smtp.host", myEmailSMTPHost);   // �����˵������ SMTP ��������ַ
	    props.setProperty("mail.smtp.auth", "true");            // ��Ҫ������֤
	    props.put("mail.smtp.ssl.enable", "true");
	    props.put("mail.smtp.ssl.socketFactory", sf);
 } 
 public void send(String receiveMail,String username,int code) throws UnsupportedEncodingException, MessagingException {
	  Session session = Session.getInstance(props);
	    session.setDebug(true);            
	    MimeMessage message = new MimeMessage(session);
	    // 2. From: �����ˣ��ǳ��й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸��ǳƣ�
//	    message.setRecipient(MimeMessage.RecipientType.CC, new InternetAddress("15067148656@163.com", "liuxiaoran", "UTF-8"));
	    message.setFrom(new InternetAddress(myEmailAccount, "����Ȼ", "UTF-8"));
	    // 3. To: �ռ��ˣ��������Ӷ���ռ��ˡ����͡����ͣ�
	    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "username", "UTF-8"));

	    // 4. Subject: �ʼ����⣨�����й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ı��⣩
	    message.setSubject("������֤��", "UTF-8");

	    // 5. Content: �ʼ����ģ�����ʹ��html��ǩ���������й�����ɣ����ⱻ�ʼ�����������Ϊ���ķ������������ʧ�ܣ����޸ķ������ݣ�
	    message.setContent("�û�:"+username+"<br />������֤��Ϊ:"+"<span color='red'>"+code+"</span>", "text/html;charset=UTF-8");
	    // 6. ���÷���ʱ��
	    message.setSentDate(new Date());
	    // 7. ��������
	    message.saveChanges();

	    // 4. ���� Session ��ȡ�ʼ��������
	    Transport transport = session.getTransport();
	    
	 // 5. ʹ�� �����˺� �� ���� �����ʼ�������, ������֤����������� message �еķ���������һ��, ���򱨴�
	    transport.connect(myEmailAccount, myEmailPassword);
	    
	 // 6. �����ʼ�, �������е��ռ���ַ, message.getAllRecipients() ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
	    transport.sendMessage(message, message.getAllRecipients());
	    /*Transport.send(message);*/
	 // 7. �ر�����
	    transport.close();
}
}
