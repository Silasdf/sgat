package application.onibus;

import java.net.URL;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Onibus;

public class CadastroOnibusController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridOnibus;
	
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
	
	//Initializes the controller class.
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	onibusService = OnibusDBService.getInstance();
    	atualizaDadosTabela();
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void pesquisaOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen10ID);
       limpar();
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
    	limpar();
    }
    
    @FXML
    private void salvar(ActionEvent event){
    	System.out.println("Onibus cadastro com sucesso!");
		Onibus onibus = new Onibus();
		pegaValores(onibus);
		onibusService.salvar(onibus);
		Mensagens.mensagemInformativa("Onibus cadastrado com sucesso!");
		atualizaDadosTabela();
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
    
	// pega os valores entrados pelo usuário e adiciona no objeto onibus
	private void pegaValores(Onibus onibus) {
		onibus.setNome(txtNome.getText());
		Double valorPoltrona = Double.parseDouble(txtValorPorPoltrona.getText());
		onibus.setValorPorPoltrona(valorPoltrona);
		onibus.setPlacaOnibus(txtPlacaOnibus.getText());
		onibus.setOnibusComMultas(txtOnibusComMultas.getText());
		int anoOnibus = Integer.parseInt(txtAnoOnibus.getText());
		onibus.setAnoOnibus(anoOnibus);
		onibus.setViagensRealizadas(txtViagensRealizadas.getValue());
		onibus.setTelefone(txtTelefone.getText());
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
		limpar();
	}

}
