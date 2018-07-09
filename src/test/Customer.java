package test;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Customer {
private String name;
private String id;
public Customer(String name,String id) {
	this.name=name;
	this.id=id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
@JsonIgnore
public String getCity() {
	return "beijing";
}
public static void main(String[] args) throws JsonProcessingException {
	//����jar��
    //����ObjectMappter����
	ObjectMapper mapper=new ObjectMapper();
   //����mapper��writeValueAsString()
	//��һ������תΪjson�ַ���
	 Customer cust=new Customer("liuxiaoran", "1");
	 String jsonStr=mapper.writeValueAsString(cust);
	 System.out.println(jsonStr);
//{"name":"liuxiaoran","id":"1","city":"beijing"}
	 //ͨ��getXXX������дjson�ַ�����
	 
	 //���@JsonIgnore���������javaBean
	 
	 
}

}
