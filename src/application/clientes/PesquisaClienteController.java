package application.clientes;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import application.textfieldformatter.TextFieldFormatter;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
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
	private Spinner<Integer> txtViagemEmpresa;
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private Button btnPesquisar;
	
	private ClientesService clientesService;
	
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	clientesService = ClientesDBService.getInstance();
    	configuraColunas();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen3ID);
		limpar();
    	tblClientes.getItems().clear();
    }
    
    @FXML
    public void pesquisar(){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
    	buscarClientes(cliente);
		Mensagens.mensagemInformativa("Cliente pesquisado com sucesso!");
    }
    
    
    private void buscarClientes(Cliente cliente) {
		tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
    }
    
    @FXML
    private void visualizar(){
    	Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
    	System.out.println(cliente);
    	clientesService.setCliente(cliente);
    	limpar();
        myController.setScreen(ScreensFramework.screen5ID);
    	tblClientes.getItems().clear();
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
    	txtViagemEmpresa.getValueFactory().setValue(0);
    	txtTelefone.setText("");
    }
	
    @FXML
    public void apagar(){
		Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
		System.out.println(cliente);
		if (Mensagens.mensagemConfirmacao("Deseja apagar este cliente?")) {
			clientesService.apagar(cliente);
	    	tblClientes.getItems().clear();
		}
    }
    
    @FXML
    private void txtCpfKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("###.###.###-##");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtCpf);
    	tff.formatter();
    }
    
    @FXML
    private void txtTelefoneKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("(##)#####-####");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtTelefone);
    	tff.formatter();
    }
    
    @FXML
    private void txtRgKeyReleased(){
    	TextFieldFormatter tff = new TextFieldFormatter();
    	tff.setMask("#.###.###");
    	tff.setCaracteresValidos("0123456789");
    	tff.setTf(txtRg);
    	tff.formatter();
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
//		if (!SgatUtills.isNullOrEmpty((txtViagemEmpresa.getText()))){
//			int viagemEmpresa = Integer.parseInt(txtViagemEmpresa.getText());
//			cliente.setViagemEmpresa(viagemEmpresa);
//		}
		if (txtViagemEmpresa.getValue()!= null && txtViagemEmpresa.getValue()!= 0){
			cliente.setViagemEmpresa(txtViagemEmpresa.getValue());
		}
		cliente.setTelefone(txtTelefone.getText());
	}

	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		clCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
		clRg.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rg"));
		clViagemEmpresa.setCellValueFactory(new PropertyValueFactory<Cliente, String>("viagemEmpresa"));
	}

}
