package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class DbUtils {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	private static int initSize;
	private static int maxActive;
	private static BasicDataSource bs=null;
	static{
		bs = new BasicDataSource();
		Properties ps = new Properties();
		InputStream in = DbUtils.class.getClassLoader().getResourceAsStream("db.propertise");
		try {
			ps.load(in);
			driver = ps.getProperty("jdbc.driver");
			username = ps.getProperty("jdbc.username");
			url=ps.getProperty("jdbc.url");
			password=ps.getProperty("jdbc.password");
			initSize = Integer.parseInt(ps.getProperty("initSize"));
			maxActive = Integer.parseInt(ps.getProperty("maxActive"));
			
			in.close();
			bs.setDriverClassName(driver);
			bs.setUrl(url);
			bs.setUsername(username);
			bs.setPassword(password);
			bs.setInitialSize(initSize);
			bs.setMaxActive(maxActive);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			conn = bs.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	public static void close(Connection conn){
		try {
			if(conn!=null){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
