package application.principal;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class InicioController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private MenuItem incluirDocumento;
	
	@FXML
	private ListView listaDocumento;
	
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
    private void cadastroHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen6ID);
    }
    
    @FXML
    private void pesquisaHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen7ID);
    }
    
    @FXML
    private void cadastroOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen9ID);
    }
    
    @FXML
    private void pesquisaOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen10ID);
    }
    
    @FXML
    public void botaoIncluir(ActionEvent event){
    	FileChooser fc = new FileChooser();
//    	File file = new File(filename);
    	
    	fc.setInitialDirectory(new File("//home//silas//Documentos//TCC - 8º Semestre"));
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("WORD Files","*.docx"));
    	File selectedFile = fc.showOpenDialog(null);
//    	File selectedFile = fc.showSaveDialog(window);
    	
    	if (selectedFile != null){
    		listaDocumento.getItems().add(selectedFile.getAbsolutePath());
//    	    Files.copy(file.toPath(), selectedFile.toPath());
    	} else {
    		System.out.println("Arquivo não é valido");
    	}
    }
    
    @FXML
    private void sair(ActionEvent event){
    	System.out.println("Sistema foi fechado!");
    	System.exit(0);
    }

}
