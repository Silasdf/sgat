package application.viagens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Viagem;

public class CadastroViagemController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private GridPane gridViagem;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private DatePicker txtDataIda;
	
	@FXML
	private DatePicker txtDataVolta;
	
	@FXML
	private TextField txtEmbarque;
	
	@FXML
	private TextField txtHospedagem;
	
	@FXML
	private TextField txtValorPoltronaOnibus;
	
	private ViagensService viagensService;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	viagensService = ViagensDBService.getInstance();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void pesquisaViagem(ActionEvent event){
       myController.setScreen(ScreensFramework.screen13ID);
       limpar();
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
    	limpar();
    }
    
    @FXML
    private void salvar(ActionEvent event){
    	System.out.println("Viagem cadastra com sucesso!");
		Viagem viagem = new Viagem();
		pegaValores(viagem);
		viagensService.salvar(viagem);
		Mensagens.mensagemInformativa("Viagem cadastrada com sucesso!");
		limpar();
    }
    
    @FXML
    private void limpar(){
    	txtNome.setText("");
    	txtDataIda.setValue(null);
    	txtDataVolta.setValue(null);
    	txtEmbarque.setText("");
    	txtHospedagem.setText("");
    	txtValorPoltronaOnibus.setText("");
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto viagem
	private void pegaValores(Viagem viagem) {
		viagem.setNome(txtNome.getText());
		LocalDate dataIda = txtDataIda.getValue();
		viagem.setDataIda(dataIda);
		LocalDate dataVolta = txtDataVolta.getValue();
		viagem.setDataVolta(dataVolta);
		viagem.setEmbarque(txtEmbarque.getText());
		viagem.setHospedagem(txtHospedagem.getText());
		Double valorPoltronaOnibus = Double.parseDouble(txtValorPoltronaOnibus.getText());
		viagem.setValorPoltronaOnibus(valorPoltronaOnibus);
	}

}
