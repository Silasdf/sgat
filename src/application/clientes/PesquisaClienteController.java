package application.clientes;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import framework.SgatUtills;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    
//    private void mensagemInformativa(Cliente cliente){
//    	
//    	btnPesquisar.setOnAction(e -> {
//        	Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
//        	dialogoInfo.setTitle("Pesquisa Cliente");
//        	dialogoInfo.setHeaderText("Esse é o cabeçalho...");
//        	dialogoInfo.setContentText("Cliente Pesquisado com sucesso!");
//        	dialogoInfo.showAndWait();
//        	Stage stage1 = (Stage) dialogoInfo.getDialogPane().getScene().getWindow();
//        	stage1.getIcons().add(new Image(this.getClass().getResource("imagens/S_1.png").toString()));
//        });
//
//    }

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
//		mensagemInformativa(cliente);
    }
    
    
    private void buscarClientes(Cliente cliente) {
		tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
    }
    
    @FXML
    private void visualizar(){
    	Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
    	System.out.println(cliente);
    	clientesService.setCliente(cliente);
        myController.setScreen(ScreensFramework.screen5ID);
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
	
    @FXML
    public void apagar(){
		Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
		System.out.println(cliente);
		clientesService.apagar(cliente);
    	tblClientes.getItems().clear();
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
//		cliente.setViagemEmpresa(txtViagemEmpresa.getText());
		if (!SgatUtills.isNullOrEmpty((txtViagemEmpresa.getText()))){
			int viagemEmpresa = Integer.parseInt(txtViagemEmpresa.getText());
			cliente.setViagemEmpresa(viagemEmpresa);
		}
//		cliente.setCodigo(codigo);
	}

	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		clCpf.setCellValueFactory(new PropertyValueFactory<Cliente, String>("cpf"));
		clRg.setCellValueFactory(new PropertyValueFactory<Cliente, String>("rg"));
		clViagemEmpresa.setCellValueFactory(new PropertyValueFactory<Cliente, String>("viagemEmpresa"));
	}

}
