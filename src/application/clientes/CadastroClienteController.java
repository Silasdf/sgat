package application.clientes;

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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Cliente;

public class CadastroClienteController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private GridPane gridCliente;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtCpf;
	
	@FXML
	private DatePicker txtDataNascimento;
	
	@FXML
	private TextField txtRg;
	
	@FXML
	private TextField txtEndereco;
	
	@FXML
	private TextField txtCidade;
	
	@FXML
	private Spinner<Integer> txtViagemEmpresa;
	
//	@FXML
//	private SpinnerValueFactory svfViagemEmpresa;
	
//	@FXML
//	private TextField txtViagemEmpresa;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private Button btnSalvar;
	
	private ClientesService clientesService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	clientesService = ClientesDBService.getInstance();
//    	atualizaDadosTabela();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
       limpar();
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
    	limpar();
    }
    
    @FXML
    private void salvar(ActionEvent event){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.salvar(cliente);
    	System.out.println("Cliente cadastro com sucesso!");
		Mensagens.mensagemInformativa("Cliente cadastrado com sucesso!");
		limpar();
    }
    
    @FXML
    private void limpar(){
    	txtNome.setText("");
    	txtCpf.setText("");
    	txtDataNascimento.setValue(null);
    	txtRg.setText("");
    	txtEndereco.setText("");
    	txtCidade.setText("");
//    	txtViagemEmpresa.setText("");
    	txtViagemEmpresa.getValueFactory().setValue(0);
    	txtTelefone.setText("");
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto cliente
	private void pegaValores(Cliente cliente) {
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		LocalDate data = txtDataNascimento.getValue();
		cliente.setDataNascimento(data);
		cliente.setRg(txtRg.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setViagemEmpresa(txtViagemEmpresa.getValue());
//		int viagemEmpresa = Integer.parseInt(txtViagemEmpresa.getText());
//		cliente.setViagemEmpresa(viagemEmpresa);
		cliente.setTelefone(txtTelefone.getText());
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
//	private void atualizaDadosTabela() {
//		limpar();
//	}
}


