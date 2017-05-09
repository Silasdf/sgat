package application.clientes;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.layout.GridPane;
import sgat.entidades.Cliente;

public class SelecionaClienteController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private TableView<Cliente> tblClientes = new TableView<Cliente>();
	
	@FXML
	private TableColumn<Cliente, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private GridPane gridCliente;
	
	@FXML
	private TextField txtNome;
	
	private ClientesService clientesService;
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	clientesService = ClientesDBService.getInstance();
	    	configuraColuna();
	    }
	 
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	    
	    @FXML
	    private void voltar(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen14ID);
	       limpar();
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNome.setText("");
			tblClientes.getItems().clear();
	    }
	    
		@FXML
		private void pesquisarCliente(ActionEvent event){
			Cliente cliente = new Cliente();
			cliente.setNome(txtNome.getText());
			tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
		}
		
		@FXML
		private void levarCliente(){
	    	Cliente cliente = tblClientes.getSelectionModel().getSelectedItem();
	    	System.out.println(cliente);
	    	clientesService.setClienteSelecionado(cliente);
	        myController.setScreen(ScreensFramework.screen14ID);
	        limpar();
		}
	    
		private void configuraColuna() {
			clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		}

}
