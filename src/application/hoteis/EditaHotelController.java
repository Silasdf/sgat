package application.hoteis;

//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
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
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import sgat.entidades.Hotel;

public class EditaHotelController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridHotel;
	
	@FXML
	private TextField txtNomeHotelEdita;
	
	@FXML
	private TextField txtCnpjEdita;
	
	@FXML
	private TextField txtValorDuploPorPessoaEdita;
	
	@FXML
	private TextField txtValorTriploPorPessoaEdita;
	
	@FXML
	private TextField txtValorQuadruploPorPessoaEdita;
	
	@FXML
	private TextField txtEnderecoHotelEdita;
	
	@FXML
	private TextField txtCidadeHotelEdita;
	
	@FXML
	private TextField txtTelefoneHotelEdita;
	
	@FXML
	private ImageView primeiraImagem;
	
	private HoteisService hoteisService;
	
	private Hotel hotelSelecionado;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	hoteisService = HoteisDBService.getInstance();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void voltar(ActionEvent event){
       myController.setScreen(ScreensFramework.screen7ID);
       limpar();
    }
    
    @FXML
    private void carrega(){
    	mudarEdicao(false);
    	hotelSelecionado = hoteisService.getHotel();
    	txtNomeHotelEdita.setText(hotelSelecionado.getNome());
    	txtCnpjEdita.setText(hotelSelecionado.getCnpj());
    	txtValorDuploPorPessoaEdita.setText(hotelSelecionado.getValorDuploPorPessoa().toString());
    	txtValorTriploPorPessoaEdita.setText(hotelSelecionado.getValorTriploPorPessoa().toString());
    	txtValorQuadruploPorPessoaEdita.setText(hotelSelecionado.getValorQuadruploPorPessoa().toString());
    	txtEnderecoHotelEdita.setText(hotelSelecionado.getEndereco());
    	txtCidadeHotelEdita.setText(hotelSelecionado.getCidade());
    	txtTelefoneHotelEdita.setText(hotelSelecionado.getTelefone());
    	System.out.println(hotelSelecionado);
//    	Codigo para carregar a imagem(s) no momento que o hotel é selecionado
//    	e vai para a tela de edição a imagem é carregada
//    	FileInputStream inputStream;
//		try {
//			inputStream = new FileInputStream("/home/silas/Imagens/Deadpool.jpg");
//			Image image = new Image(inputStream);
//			primeiraImagem.setImage(image);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
    	
    }
    
    @FXML
    private void salvar(ActionEvent event){
		Hotel hotel = new Hotel();
		pegaValores(hotel);
		hoteisService.atualizar(hotel);
		Mensagens.mensagemInformativa("Hotel alterado com sucesso!");
    	mudarEdicao(false);
    	System.out.println("hotel Editado: " + hotel );
    }
    
    @FXML
    private void editar(){
    	mudarEdicao(true);
    }
    
    private void mudarEdicao(Boolean novoEstado){
    	txtNomeHotelEdita.setEditable(novoEstado);
    	txtCnpjEdita.setEditable(novoEstado);
    	txtValorDuploPorPessoaEdita.setEditable(novoEstado);
    	txtValorTriploPorPessoaEdita.setEditable(novoEstado);
    	txtValorQuadruploPorPessoaEdita.setEditable(novoEstado);
    	txtEnderecoHotelEdita.setEditable(novoEstado);
    	txtCidadeHotelEdita.setEditable(novoEstado);
    	txtTelefoneHotelEdita.setEditable(novoEstado);
    }
    
    @FXML
    private void limpar(){
    	txtNomeHotelEdita.setText("");
    	txtCnpjEdita.setText("");
    	txtValorDuploPorPessoaEdita.setText("");
    	txtValorTriploPorPessoaEdita.setText("");
    	txtValorQuadruploPorPessoaEdita.setText("");
    	txtEnderecoHotelEdita.setText("");
    	txtCidadeHotelEdita.setText("");
    	txtTelefoneHotelEdita.setText("");
    	primeiraImagem.setImage(null);
    }
    
    @FXML
    private void txtCnpjKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("##.###.###/####-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtCnpjEdita);
    	tff.formatter();
    }
    
    @FXML
    private void txtTelefoneHotelKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("(##)####-####");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtTelefoneHotelEdita);
    	tff.formatter();
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto hotel
	private void pegaValores(Hotel hotel) {
		hotel.setNome(txtNomeHotelEdita.getText());
		hotel.setCnpj(txtCnpjEdita.getText());
		Double valorDuplo = Double.parseDouble(txtValorDuploPorPessoaEdita.getText());
		hotel.setValorDuploPorPessoa(valorDuplo);
		Double valorTriplo = Double.parseDouble(txtValorTriploPorPessoaEdita.getText());
		hotel.setValorTriploPorPessoa(valorTriplo);
		Double valorQuadruplo = Double.parseDouble(txtValorQuadruploPorPessoaEdita.getText());
		hotel.setValorQuadruploPorPessoa(valorQuadruplo);
		hotel.setEndereco(txtEnderecoHotelEdita.getText());
		hotel.setCidade(txtCidadeHotelEdita.getText());
		hotel.setTelefone(txtTelefoneHotelEdita.getText());
		hotel.setCodigo(hotelSelecionado.getCodigo());
		hotel.setAtivo(hotelSelecionado.getAtivo());
	}

}
