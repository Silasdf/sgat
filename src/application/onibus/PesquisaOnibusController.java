package application.onibus;

import java.net.URL;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import framework.SgatUtills;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sgat.entidades.Onibus;

public class PesquisaOnibusController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private TableView<Onibus> tblOnibus = new TableView<Onibus>();
	
	@FXML
	private TableColumn<Onibus, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private TableColumn<Onibus, String> clValorPorPoltrona = new TableColumn<>("ValorPorPoltrona");
	
	@FXML
	private TableColumn<Onibus, String> clTelefone = new TableColumn<>("Telefone");
	
	@FXML
	private TableColumn<Onibus, String> clViagensRealizadas = new TableColumn<>("ViagensRealizadas");
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtValorPorPoltrona;
	
	@FXML
	private TextField txtPlacaOnibus;
	
	@FXML
	private TextField txtOnibusComMultas;
	
	@FXML
	private TextField txtAnoOnibus;
	
	@FXML
	private Spinner<Integer> txtViagensRealizadas;
	
	@FXML
	private TextField txtTelefone;
	
	private OnibusService onibusService;
	
	   @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	onibusService = OnibusDBService.getInstance();
	    	configuraColunas();
	    }
	    
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }

	    @FXML
	    private void voltar(ActionEvent event){
	    	myController.setScreen(ScreensFramework.screen9ID);
			limpar();
	    	tblOnibus.getItems().clear();
	    }
	    
	    @FXML
	    public void pesquisar(){
			Onibus onibus = new Onibus();
			pegaValores(onibus);
	    	buscarOnibus(onibus);
			Mensagens.mensagemInformativa("Onibus pesquisado com sucesso!");
	    }
	    
	    
	    private void buscarOnibus(Onibus onibus) {
			tblOnibus.getItems().setAll(onibusService.buscarOnibus(onibus));
	    }
	    
	    @FXML
	    private void visualizar(){
	    	Onibus onibus = tblOnibus.getSelectionModel().getSelectedItem();
	    	System.out.println(onibus);
	    	onibusService.setOnibus(onibus);
	        myController.setScreen(ScreensFramework.screen11ID);
	    	tblOnibus.getItems().clear();
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNome.setText("");
	    	txtValorPorPoltrona.setText("");
	    	txtPlacaOnibus.setText("");
	    	txtOnibusComMultas.setText("");
	    	txtAnoOnibus.setText("");
	    	txtViagensRealizadas.getValueFactory().setValue(0);
	    	txtTelefone.setText("");
	    }
	    
	    @FXML
	    public void apagar(){
			Onibus onibus = tblOnibus.getSelectionModel().getSelectedItem();
			System.out.println(onibus);
			if (Mensagens.mensagemConfirmacao("Deseja apagar este onibus?")) {
				onibusService.apagar(onibus);
		    	tblOnibus.getItems().clear();
			}
	    }
	    
		// pega os valores entrados pelo usuário e adiciona no objeto onibus
		private void pegaValores(Onibus onibus) {
			onibus.setNome(txtNome.getText());
			if (!SgatUtills.isNullOrEmpty((txtValorPorPoltrona.getText()))){
				Double valorPoltrona = Double.parseDouble(txtValorPorPoltrona.getText());
				onibus.setValorPorPoltrona(valorPoltrona);
			}
			onibus.setPlacaOnibus(txtPlacaOnibus.getText());
			onibus.setOnibusComMultas(txtOnibusComMultas.getText());
			if (!SgatUtills.isNullOrEmpty((txtAnoOnibus.getText()))){
			int anoOnibus = Integer.parseInt(txtAnoOnibus.getText());
			onibus.setAnoOnibus(anoOnibus);
			}
			if (txtViagensRealizadas.getValue()!= null && txtViagensRealizadas.getValue()!= 0){
			onibus.setViagensRealizadas(txtViagensRealizadas.getValue());
			}
			onibus.setTelefone(txtTelefone.getText());
		}
		
		private void configuraColunas() {
			clNome.setCellValueFactory(new PropertyValueFactory<Onibus, String>("nome"));
			clValorPorPoltrona.setCellValueFactory(new PropertyValueFactory<Onibus, String>("valorPorPoltrona"));
			clTelefone.setCellValueFactory(new PropertyValueFactory<Onibus, String>("telefone"));
			clViagensRealizadas.setCellValueFactory(new PropertyValueFactory<Onibus, String>("viagensRealizadas"));
		}
		
}
