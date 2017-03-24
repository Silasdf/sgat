package framework;

import java.sql.Connection;
import java.sql.DriverManager;

public class MariaDBConection {
	
	public static Connection Connector(){
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sgatdb?user=vfturismo&password=vfturismo");
			return conn;
		} catch (Exception e){
			System.out.println(e);
			return null;
		}
	}

}
