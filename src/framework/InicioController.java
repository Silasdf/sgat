package framework;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class InicioController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void goToCadastroCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen3ID);
    }
    
    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
    }
    
    @FXML
    private void sair(ActionEvent event){
    	System.out.println("Sistema foi fechado!");
    	System.exit(0);
    }

}
