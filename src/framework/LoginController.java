package framework;

import java.net.URL;
import java.util.ResourceBundle;

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
	 }
	 
//	 public void Login (ActionEvent event){
//		 try{
//			 if (loginModel.isLogin(txtNomeUsuario.getText(), txtSenhaUsuario.getText())){
//				 isConnected.setText("Usuário e senha corretos");
//			  } else {
//
//			   isConnected.setText("Usuário e senha incorretos");
//			  }
//			 
//		 } catch (SQLException e){
//			 isConnected.setText("Usuário e senha incorretos");
//			 e.printStackTrace();
//		 }
//	 }
	 
	    @FXML
	    private void goToCadastroCliente(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen2ID);
	    }
	  
	}