package framework;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import sgat.entidades.Cliente;

public class PesquisaClienteController implements Initializable, ControlledScreen {
	
	ScreensController myController;

	@FXML
	private TableView<Cliente> tblClientes;
	
	@FXML
	private TableColumn<Cliente, String> c1Nome;
	
	@FXML
	private TableColumn<Cliente, String> c1Cpf;
	
	@FXML
	private TableColumn<Cliente, String> c1Rg;
	
	@FXML
	private TableColumn<Cliente, String> c1ViagemEmpresa;
	
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
	
	private List<Cliente> clientes;
	
	private ClientesService clientesService;
	
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	clientesService = ClientesService.getNewInstance();
    	atualizaDadosTabela();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToCadastroCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen2ID);
    }
    
//    @FXML
//    private void goToScreen3(ActionEvent event){
//       myController.setScreen(ScreensFramework.screen3ID);
//    }
    
    @FXML
    public List<Cliente> pesquisar(){
    	return clientes;
    }
    
    @FXML
    private void limpar(){
    	System.out.println("Cliquei em limpar!");
    	txtNome.setText("");
    	txtCpf.setText("");
    	txtDataNascimento.setValue(null);
    	txtRg.setText("");
    	txtEndereco.setText("");
    	txtCidade.setText("");
    	txtViagemEmpresa.setText("");
    }
    
//	// pega os valores entrados pelo usuário e adiciona no objeto conta
//	private void pegaValores(Cliente cliente) {
//		cliente.setNome(txtNome.getText());
//		cliente.setCpf(txtCpf.getText());
//		LocalDate data = txtDataNascimento.getValue();
//		cliente.setDataNascimento(data);
//		cliente.setRg(txtRg.getText());
//		cliente.setEndereco(txtEndereco.getText());
//		cliente.setCidade(txtCidade.getText());
//		cliente.setViagemEmpresa(txtViagemEmpresa.getText());
//	}
    
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
		tblClientes.getItems().setAll(clientesService.buscarTodos());
//		limpar();
	}

}
