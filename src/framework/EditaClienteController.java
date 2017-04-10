package framework;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Cliente;

public class EditaClienteController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridCliente;
	
	@FXML
	private TextField txtNomeEdita;
	
	@FXML
	private TextField txtCpfEdita;
	
	@FXML
	private DatePicker txtDataNascimentoEdita;
	
	@FXML
	private TextField txtRgEdita;
	
	@FXML
	private TextField txtEnderecoEdita;
	
	@FXML
	private TextField txtCidadeEdita;
	
	@FXML
	private TextField txtViagemEmpresaEdita;
	
	@FXML
	private TextField txtArmazenaCodigo;
	
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
//    	carrega();
    }
    
//    public final void setOnShown(EventHandler<WindowEvent> value) {
//    	System.out.println("Evento disparado: ");
//    }
    
//    final ContextMenu contextMenu = new ContextMenu();
//    
//    contextMenu.setOnShown(new EventHandler<WindowEvent>(){
//    	public void handle(WindowEvent e){
//    	System.out.println("Evento disparado: ");
//    	}
//    });
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
       limpar();
    }
    
    @FXML
    private void sair(ActionEvent event){
    	System.out.println("Sistema foi fechado!");
    	System.exit(0);
    }
    
    @FXML
    private void carrega(){
    	Cliente cliente = clientesService.getCliente();
    	txtNomeEdita.setText(cliente.getNome());
    	txtCpfEdita.setText(cliente.getCpf());
    	txtDataNascimentoEdita.setValue(cliente.getDataNascimento());
    	txtRgEdita.setText(cliente.getRg());
    	txtEnderecoEdita.setText(cliente.getEndereco());
    	txtCidadeEdita.setText(cliente.getCidade());
    	txtViagemEmpresaEdita.setText(cliente.getViagemEmpresa());
    	System.out.println(cliente);
    }
    
    @FXML
    private void editar(ActionEvent event){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.atualizar(cliente);
    	System.out.println("cliente Editado: " + cliente + cliente.getCodigo());

    }
    
    @FXML
    private void limpar(){
    	txtNomeEdita.setText("");
    	txtCpfEdita.setText("");
    	txtDataNascimentoEdita.setValue(null);
    	txtRgEdita.setText("");
    	txtEnderecoEdita.setText("");
    	txtCidadeEdita.setText("");
    	txtViagemEmpresaEdita.setText("");
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto cliente
	private void pegaValores(Cliente cliente) {
		cliente.setNome(txtNomeEdita.getText());
		cliente.setCpf(txtCpfEdita.getText());
		LocalDate data = txtDataNascimentoEdita.getValue();
		cliente.setDataNascimento(data);
		cliente.setRg(txtRgEdita.getText());
		cliente.setEndereco(txtEnderecoEdita.getText());
		cliente.setCidade(txtCidadeEdita.getText());
		cliente.setViagemEmpresa(txtViagemEmpresaEdita.getText());
		String clientec = "";
		System.out.println(txtArmazenaCodigo);
		int codigo = Integer.parseInt(txtArmazenaCodigo.getText());
		cliente.setCodigo(codigo);
	}
	
}
