package test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class createMail {
public static void main(String[] args) throws MessagingException, IOException {
	Properties props=new Properties();  // ���������ʼ��������Ĳ������ã������ʼ�ʱ����Ҫ�õ���
	Session session=Session.getInstance(props);//���ݲ������ã������Ự����Ϊ�˷����ʼ�׼����
	MimeMessage message = new MimeMessage(session);  // �����ʼ�����
	 /*
     * Ҳ���Ը������е�eml�ʼ��ļ����� MimeMessage ����
     * MimeMessage message = new MimeMessage(session, new FileInputStream("MyEmail.eml"));
     */
	
	// 2. From: ������
    //    ���� InternetAddress �����������ֱ�Ϊ: ����, ��ʾ���ǳ�(ֻ������ʾ, û���ر��Ҫ��), �ǳƵ��ַ�������
    //    ����Ҫ����ʱ, �����������ʵ��Ч�����䡣
    message.setFrom(new InternetAddress("15067148656@163.com", "liuxiaoran", "UTF-8"));
    
    // 3. To: �ռ���
    message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress("512748799@qq.com", "USER", "UTF-8"));
    
 // 4. Subject: �ʼ�����
    message.setSubject("��ϲ��������ɹ���", "UTF-8");
    
    // 5. Content: �ʼ����ģ�����ʹ��html��ǩ��
    message.setContent("������֤��Ϊ��xxxx", "text/html;charset=UTF-8");
    
    // 6. ������ʾ�ķ���ʱ��
    message.setSentDate(new Date());
    
 // 7. ����ǰ�������
    message.saveChanges();

 // 8. �����ʼ����浽����
    OutputStream out = new FileOutputStream("E:\\workspace\\example1\\WebContent\\mail\\myMail.eml");
    message.writeTo(out);
    out.flush();
    out.close();
}
}
