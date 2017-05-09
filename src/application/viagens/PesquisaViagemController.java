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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sgat.entidades.Viagem;

public class PesquisaViagemController implements Initializable, ControlledScreen{
	
	ScreensController myController;

	@FXML
	private TableView<Viagem> tblViagens = new TableView<Viagem>();
	
	@FXML
	private TableColumn<Viagem, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private TableColumn<Viagem, String> clDataIda = new TableColumn<>("DataIda");
	
	@FXML
	private TableColumn<Viagem, String> clDataVolta = new TableColumn<>("DataVolta");
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private DatePicker txtDataIda;
	
	@FXML
	private DatePicker txtDataVolta;
	
	private ViagensService viagensService;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	viagensService = ViagensDBService.getInstance();
    	configuraColunas();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen12ID);
		limpar();
    	tblViagens.getItems().clear();
    }
    
    @FXML
    public void pesquisar(){
		Viagem viagem = new Viagem();
		pegaValores(viagem);
    	buscarViagens(viagem);
		Mensagens.mensagemInformativa("Viagem pesquisada com sucesso!");
    }
    
    private void buscarViagens(Viagem viagem) {
		tblViagens.getItems().setAll(viagensService.buscarViagens(viagem));
    }
    
    @FXML
    private void visualizar(){
    	Viagem viagem = tblViagens.getSelectionModel().getSelectedItem();
    	System.out.println(viagem);
    	viagensService.setViagem(viagem);
        myController.setScreen(ScreensFramework.screen14ID);
    }
    
	@FXML
    private void limpar(){
    	System.out.println("Cliquei em limpar!");
    	txtNome.setText("");
    	txtDataIda.setValue(null);
    	txtDataVolta.setValue(null);
    }
	
    @FXML
    public void apagar(){
		Viagem viagem = tblViagens.getSelectionModel().getSelectedItem();
		System.out.println(viagem);
		if (Mensagens.mensagemConfirmacao("Deseja apagar esta Viagem?")) {
			viagensService.apagar(viagem);
	    	tblViagens.getItems().clear();
		}
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto viagem
	private void pegaValores(Viagem viagem) {
		viagem.setNome(txtNome.getText());
		LocalDate dataIda = txtDataIda.getValue();
		viagem.setDataIda(dataIda);
		LocalDate dataVolta = txtDataVolta.getValue();
		viagem.setDataVolta(dataVolta);
	}

	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Viagem, String>("nome"));
		clDataIda.setCellValueFactory(new PropertyValueFactory<Viagem, String>("dataIda"));
		clDataVolta.setCellValueFactory(new PropertyValueFactory<Viagem, String>("dataVolta"));
	}
	
}
