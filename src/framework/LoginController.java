package framework;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class LoginController implements Initializable, ControlledScreen {
	
	public LoginModel loginModel = new LoginModel();
	ScreensController myController;
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
	 
	   @FXML
	   private Label isConnected;
	 @Override
	 public void initialize(URL url, ResourceBundle rb) {
	  // TODO Auto-generated method stub
	  if (loginModel.isDbConnected()) {
	   isConnected.setText("Connected");
	  } else {

	   isConnected.setText("Not Connected");
	  }
	 }
	  
	}