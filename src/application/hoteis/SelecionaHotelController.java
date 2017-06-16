package application.hoteis;

import java.net.URL;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import sgat.entidades.Hotel;

public class SelecionaHotelController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private TableView<Hotel> tblHoteis = new TableView<Hotel>();
	
	@FXML
	private TableColumn<Hotel, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private GridPane gridHotel;
	
	@FXML
	private TextField txtNome;
	
	private HoteisService hoteisService;
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	hoteisService = HoteisDBService.getInstance();
	    	configuraColuna();
	    }
	 
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	    
	    @FXML
	    private void voltar(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen14ID);
	       limpar();
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNome.setText("");
			tblHoteis.getItems().clear();
	    }
	    
		@FXML
		private void pesquisarHotel(ActionEvent event){
			Hotel hotel = new Hotel();
			hotel.setNome(txtNome.getText());
			tblHoteis.getItems().setAll(hoteisService.buscarHoteis(hotel));
		}
		
		@FXML
		private void levarHotel(){
	    	Hotel hotel = tblHoteis.getSelectionModel().getSelectedItem();
	    	System.out.println(hotel);
	    	hoteisService.setHotelSelecionado(hotel);
	        myController.setScreen(ScreensFramework.screen14ID);
	        limpar();
		}
	    
		private void configuraColuna() {
			clNome.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nome"));
		}

}
