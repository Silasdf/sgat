package application.principal;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController implements Initializable, ControlledScreen {
	
	public LoginModel loginModel = new LoginModel();
	ScreensController myController;
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
	 
	   @FXML
	   private Label isConnected;
	   
	   @FXML
	   private TextField txtNomeUsuario;
	   
	   @FXML
	   private TextField txtSenhaUsuario;
	   
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
	  // TODO Auto-generated method stub
	  if (loginModel.isDbConnected()) {
	   isConnected.setText("Connected");
	  } else {

	   isConnected.setText("Not Connected");
	  }
  	txtNomeUsuario.setText("vfturismo");
  	txtSenhaUsuario.setText("vfturismo");
	 }
	 
	 public void login (ActionEvent event){
		 try{
			 if (loginModel.isLogin(txtNomeUsuario.getText(), txtSenhaUsuario.getText())){
				 isConnected.setText("Usuário e senha corretos");
				 goToCadastroCliente();
			  } else {

			   isConnected.setText("Usuário e senha incorretos");
			  }
			 
		 } catch (SQLException e){
			 isConnected.setText("Usuário e senha incorretos");
			 e.printStackTrace();
		 }
	 }
	 
	    private void goToCadastroCliente(){
	       myController.setScreen(ScreensFramework.screen2ID);
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNomeUsuario.setText("");
	    	txtSenhaUsuario.setText("");
	    }
	  
	}