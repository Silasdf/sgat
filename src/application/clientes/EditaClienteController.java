package application.clientes;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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

//	private TextField txtArmazenaCodigo;
	
	@FXML
	private Button sair;
	
	private ClientesService clientesService;
	
	private Cliente clienteSelecionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	clientesService = ClientesDBService.getInstance();
    	System.out.println("Initialize do edita às " + new Date());
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void voltar(ActionEvent event){
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
    	mudarEdicao(false);
    	clienteSelecionado = clientesService.getCliente();
    	txtNomeEdita.setText(clienteSelecionado.getNome());
    	txtCpfEdita.setText(clienteSelecionado.getCpf());
    	txtDataNascimentoEdita.setValue(clienteSelecionado.getDataNascimento());
    	txtRgEdita.setText(clienteSelecionado.getRg());
    	txtEnderecoEdita.setText(clienteSelecionado.getEndereco());
    	txtCidadeEdita.setText(clienteSelecionado.getCidade());
    	txtViagemEmpresaEdita.setText(clienteSelecionado.getViagemEmpresa().toString());
    	System.out.println(clienteSelecionado);
    }
    
    @FXML
    private void salvar(ActionEvent event){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.atualizar(cliente);
    	mudarEdicao(false);
    	System.out.println("cliente Editado: " + cliente );

    }
    
    @FXML
    private void editar(){
    	mudarEdicao(true);
    }
    
    private void mudarEdicao(Boolean novoEstado){
    	txtNomeEdita.setEditable(novoEstado);
    	txtCpfEdita.setEditable(novoEstado);
    	txtDataNascimentoEdita.setEditable(novoEstado);
    	txtRgEdita.setEditable(novoEstado);
    	txtEnderecoEdita.setEditable(novoEstado);
    	txtCidadeEdita.setEditable(novoEstado);
    	txtViagemEmpresaEdita.setEditable(novoEstado);
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
//		cliente.setViagemEmpresa(txtViagemEmpresaEdita.getText());
		int viagemEmpresa = Integer.parseInt(txtViagemEmpresaEdita.getText());
		cliente.setViagemEmpresa(viagemEmpresa);
		cliente.setCodigo(clienteSelecionado.getCodigo());
	}
	
}
