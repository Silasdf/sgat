package application.hoteis;

import java.net.URL;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
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
import sgat.entidades.Hotel;

public class PesquisaHotelController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private TableView<Hotel> tblHoteis = new TableView<Hotel>();
	
	@FXML
	private TableColumn<Hotel, String> clNomeHotel = new TableColumn<>("NomeHotel");
	
	@FXML
	private TableColumn<Hotel, String> clCnpj = new TableColumn<>("Cnpj");
	
	@FXML
	private TableColumn<Hotel, String> clCidade = new TableColumn<>("Cidade");
	
	@FXML
	private TableColumn<Hotel, String> clTelefone = new TableColumn<>("Telefone");
	
	@FXML
	private TextField txtNomeHotel;
	
	@FXML
	private TextField txtCnpj;
	
	@FXML
	private TextField txtValorDuploPorPessoa;
	
	@FXML
	private TextField txtValorTriploPorPessoa;
	
	@FXML
	private TextField txtValorQuadruploPorPessoa;
	
	@FXML
	private TextField txtEnderecoHotel;
	
	@FXML
	private TextField txtCidadeHotel;
	
	@FXML
	private TextField txtTelefoneHotel;
	
	private HoteisService hoteisService;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	hoteisService = HoteisDBService.getInstance();
    	configuraColunas();
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen6ID);
		limpar();
    	tblHoteis.getItems().clear();
    }
    
    @FXML
    public void pesquisar(){
		Hotel hotel = new Hotel();
		pegaValores(hotel);
    	buscarHoteis(hotel);
		Mensagens.mensagemInformativa("Hotel pesquisado com sucesso!");
    }
    
    @FXML
    private void visualizar(){
    	Hotel hotel = tblHoteis.getSelectionModel().getSelectedItem();
    	System.out.println(hotel);
    	hoteisService.setHotel(hotel);
        myController.setScreen(ScreensFramework.screen8ID);
    }
    
    private void buscarHoteis(Hotel hotel){
		tblHoteis.getItems().setAll(hoteisService.buscarHoteis(hotel));
    }
    
    @FXML
    private void limpar(){
    	txtNomeHotel.setText("");
    	txtCnpj.setText("");
    	txtValorDuploPorPessoa.setText("");
    	txtValorTriploPorPessoa.setText("");
    	txtValorQuadruploPorPessoa.setText("");
    	txtEnderecoHotel.setText("");
    	txtCidadeHotel.setText("");
    	txtTelefoneHotel.setText("");
    }
    
    @FXML
    public void apagar(){
		Hotel hotel = tblHoteis.getSelectionModel().getSelectedItem();
		System.out.println(hotel);
		if (Mensagens.mensagemConfirmacao("Deseja apagar este hotel?")) {
			hoteisService.apagar(hotel);
	    	tblHoteis.getItems().clear();
		}
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto hotel
	private void pegaValores(Hotel hotel) {
		hotel.setNome(txtNomeHotel.getText());
		hotel.setCnpj(txtCnpj.getText());
		hotel.setValorDuploPorPessoa(txtValorDuploPorPessoa.getText());
		hotel.setValorTriploPorPessoa(txtValorTriploPorPessoa.getText());
		hotel.setValorQuadruploPorPessoa(txtValorQuadruploPorPessoa.getText());
		hotel.setEndereco(txtEnderecoHotel.getText());
		hotel.setCidade(txtCidadeHotel.getText());
		hotel.setTelefone(txtTelefoneHotel.getText());
	}
	
	private void configuraColunas() {
		clNomeHotel.setCellValueFactory(new PropertyValueFactory<Hotel, String>("nome"));
		clCnpj.setCellValueFactory(new PropertyValueFactory<Hotel, String>("cnpj"));
		clCidade.setCellValueFactory(new PropertyValueFactory<Hotel, String>("cidade"));
		clTelefone.setCellValueFactory(new PropertyValueFactory<Hotel, String>("telefone"));
	}

}
