package framework;

import java.sql.Connection;
import java.sql.SQLException;

public class LoginModel {
	
	Connection conection;
	  public LoginModel () {
	   conection = MariaDBConection.Connector();
	   if (conection == null) {

	   System.out.println("connection not successful");
	    System.exit(1);}
	  }
	  
	  public boolean isDbConnected() {
	   try {
	  return !conection.isClosed();
	 } catch (SQLException e) {
	  // TODO Auto-generated catch block
	  e.printStackTrace();
	  return false;
	 }
	}
	}