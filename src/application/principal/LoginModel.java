package application.principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import framework.MariaDBConection;

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
	  
	  public boolean isLogin(String user, String pass) throws SQLException{
		  
		  PreparedStatement preparedStatement = null;
		  ResultSet resultSet = null;
		  String query = "select * from usuariosaplicacao where usuario = ? and senha = ?";
		  
		  try {
			  preparedStatement = conection.prepareStatement(query);
			  preparedStatement.setString(1, user);
			  preparedStatement.setString(2, pass);
			  
			  resultSet = preparedStatement.executeQuery();
			 
			  if (resultSet.next()){
				  return true;
			  }
			  
			  else {
				  return false;
			  }
			  
		  }catch (Exception e){
			  return false;
			  
		  } finally{
			  preparedStatement.close();
			  resultSet.close();
			  
		  }
		  
	  }
	  
	}