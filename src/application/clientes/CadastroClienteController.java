package application.clientes;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
	private TextField txtViagemEmpresa;
	
	@FXML
	private Button sair;
	
	private ClientesService clientesService;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	clientesService = ClientesDBService.getInstance();
    	atualizaDadosTabela();
//    	atualizaDadosGrid();
    	
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
    }
    
    @FXML
    private void salvar(ActionEvent event){
    	System.out.println("Cliente cadastro com sucesso!");
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.salvar(cliente);
		atualizaDadosTabela();
    }
    
    @FXML
    private void limpar(){
    	txtNome.setText("");
    	txtCpf.setText("");
    	txtDataNascimento.setValue(null);
    	txtRg.setText("");
    	txtEndereco.setText("");
    	txtCidade.setText("");
    	txtViagemEmpresa.setText("");
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
//		cliente.setViagemEmpresa(txtViagemEmpresa.getText());
		int viagemEmpresa = Integer.parseInt(txtViagemEmpresa.getText());
		cliente.setViagemEmpresa(viagemEmpresa);
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
//		tblClientes.getItems().setAll(clientesService.buscarTodos());
		limpar();
	}
	
//	private void atualizaDadosGrid(){
//		gridCliente.getChildren().addAll(clientesService.buscarTodos());
//	}

}


