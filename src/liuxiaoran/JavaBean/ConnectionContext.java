package liuxiaoran.JavaBean;

import java.sql.Connection;

public class ConnectionContext {
	private ConnectionContext() {}
static 	public ConnectionContext getInstance() {
		return instance;
	}
	private static ConnectionContext instance =new ConnectionContext();
private ThreadLocal<Connection> connThreadLocal=new ThreadLocal<>();
public void bind(Connection conn) {
  connThreadLocal.set(conn);
}
public Connection get() {
	return connThreadLocal.get();
}

public void remove() {
	connThreadLocal.remove();
}
}
