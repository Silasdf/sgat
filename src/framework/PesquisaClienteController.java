package framework;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sgat.entidades.Cliente;

public class PesquisaClienteController implements Initializable, ControlledScreen {
	
	ScreensController myController;

	@FXML
	private TableView<Cliente> tblClientes = new TableView<Cliente>();
	
	@FXML
	private TableColumn<Cliente, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private TableColumn<Cliente, String> clCpf = new TableColumn<>("Cpf");
	
	@FXML
	private TableColumn<Cliente, String> clRg = new TableColumn<>("Rg");
	
	@FXML
	private TableColumn<Cliente, String> clViagemEmpresa = new TableColumn<>("ViagemPelaEmpresa");
	
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
//    	tblClientes.getColumns().addAll(clNome, clCpf, clRg, clViagemEmpresa);
    	clientesService = ClientesDBService.getInstance();
    	configuraColunas();
        ObservableList<Cliente> clientesObservable = FXCollections.observableArrayList(clientesService.buscarTodos());
//    	atualizaDadosTabela();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToCadastroCliente(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen2ID);
		limpar();
    	tblClientes.getItems().clear();
    }
    
    @FXML
    private void goToEditaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
    }
    
    @FXML
    public void pesquisar(){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
    	buscarClientes(cliente);
//    	atualizaDadosTabela();
    }
    
    @FXML
    private void visualizar(){
    	Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
    	System.out.println(cliente);
    	clientesService.setCliente(cliente);
        myController.setScreen(ScreensFramework.screen4ID);
//    	goToEditaCliente();
//    	txtNome.setText(cliente.getNome());
//    	txtCpf.setText(cliente.getCpf());
//    	txtDataNascimento.setValue(cliente.getDataNascimento());
//    	txtRg.setText(cliente.getRg());
//    	txtEndereco.setText(cliente.getEndereco());
//    	txtCidade.setText(cliente.getCidade());
//    	txtViagemEmpresa.setText(cliente.getViagemEmpresa());
    }
    
    private void buscarClientes(Cliente cliente) {
	// TODO Auto-generated method stub
		tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
	
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
    
	// pega os valores entrados pelo usuário e adiciona no objeto conta
	private void pegaValores(Cliente cliente) {
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		LocalDate data = txtDataNascimento.getValue();
		cliente.setDataNascimento(data);
		cliente.setRg(txtRg.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setViagemEmpresa(txtViagemEmpresa.getText());
	}

	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		clCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
		clRg.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rg"));
		clViagemEmpresa.setCellValueFactory(new PropertyValueFactory<Cliente, String>("viagemEmpresa"));
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
//	private void atualizaDadosTabela() {
//		tblClientes.getItems().setAll(clientesService.buscarTodos());
////		limpar();
//	}

}
