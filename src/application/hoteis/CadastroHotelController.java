package application.hoteis;

import java.net.URL;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import application.textfieldformatter.TextFieldFormatter;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Hotel;

public class CadastroHotelController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridCliente;
	
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
    	atualizaDadosTabela();
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void pesquisaHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen7ID);
       limpar();
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
    	limpar();
    }
    
    @FXML
    private void salvar(ActionEvent event){
		Hotel hotel = new Hotel();
		pegaValores(hotel);
		hoteisService.salvar(hotel);
    	System.out.println("Hotel cadastro com sucesso!");
		Mensagens.mensagemInformativa("Hotel cadastrado com sucesso!");
		atualizaDadosTabela();
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
    private void txtCnpjKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("##.###.###/####-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtCnpj);
    	tff.formatter();
    }
    
    @FXML
    private void txtTelefoneHotelKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("(##)####-####");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtTelefoneHotel);
    	tff.formatter();
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto hotel
	private void pegaValores(Hotel hotel) {
		hotel.setNome(txtNomeHotel.getText());
		hotel.setCnpj(txtCnpj.getText());
		Double valorDuplo = Double.parseDouble(txtValorDuploPorPessoa.getText());
		hotel.setValorDuploPorPessoa(valorDuplo);
		Double valorTriplo = Double.parseDouble(txtValorTriploPorPessoa.getText());
		hotel.setValorTriploPorPessoa(valorTriplo);
		Double valorQuadruplo = Double.parseDouble(txtValorQuadruploPorPessoa.getText());
		hotel.setValorQuadruploPorPessoa(valorQuadruplo);
		hotel.setEndereco(txtEnderecoHotel.getText());
		hotel.setCidade(txtCidadeHotel.getText());
		hotel.setTelefone(txtTelefoneHotel.getText());
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
//		tblClientes.getItems().setAll(clientesService.buscarTodos());
		limpar();
	}

}
