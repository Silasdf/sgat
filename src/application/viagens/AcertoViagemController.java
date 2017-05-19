package application.viagens;

import java.net.URL;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AcertoViagemController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridAcerto;
	
	@FXML
	private TextField quantidadePacotesVendidos;
	
	@FXML
	private TextField valorTotalPacotes;
	
	@FXML
	private TextField quantidadePoltronasVendidos;
	
	@FXML
	private TextField valorTotalPoltronas;
	
	@FXML
	private TextField quantidadePacotesVendidosHotel1;
	
	@FXML
	private TextField valorTotalPacotesHotel1;
	
	@FXML
	private TextField quantidadePacotesVendidosHotel2;
	
	@FXML
	private TextField valorTotalPacotesHotel2;
	
	@FXML
	private TextField quantidadePacotesVendidosHotel3;
	
	@FXML
	private TextField valorTotalPacotesHotel3;
	
	@FXML
	private TextField demaisDespesas1;
	
	@FXML
	private TextField demaisDespesas2;
	
	@FXML
	private TextField demaisDespesas3;
	
	@FXML
	private TextField valorTotalLucro;
	
	private ViagensService viagensService;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	viagensService = ViagensDBService.getInstance();
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void calcularAcerto(ActionEvent event){
    	
    }
    
    @FXML
    private void imprimir(ActionEvent event){
    	
    }
    
    @FXML
    private void limpar(ActionEvent event){
    	
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen13ID);
    }
    
}
