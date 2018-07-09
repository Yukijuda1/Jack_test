package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class helloworldHandler implements InvocationHandler{
private helloworldDAO target;
	public helloworldHandler(helloworldDAO target) {
		// TODO Auto-generated constructor stub
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before..");
		Object result=method.invoke(target, args);
		System.out.println("after");
		return result;
	}
}
