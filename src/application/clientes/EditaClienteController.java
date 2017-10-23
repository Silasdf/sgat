package application.clientes;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
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
	private Spinner<Integer> txtViagemEmpresaEdita;

	@FXML
	private TextField txtTelefoneEdita;
	
	@FXML
	private Button sair;
	
	private ClientesService clientesService;
	
	private Cliente clienteSelecionado;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	clientesService = ClientesDBService.getInstance();
    	System.out.println("Initialize do edita às " + new Date());
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }

    @FXML
    private void voltar(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
//       limpar();
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
    	txtViagemEmpresaEdita.getValueFactory().setValue(clienteSelecionado.getViagemEmpresa());
    	txtTelefoneEdita.setText(clienteSelecionado.getTelefone());
    	System.out.println(clienteSelecionado);
    }
    
    @FXML
    private void salvar(ActionEvent event){
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.atualizar(cliente);
		Mensagens.mensagemInformativa("Cliente alterado com sucesso!");
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
    	txtTelefoneEdita.setEditable(novoEstado);
    }
    
//    @FXML
//    private void limpar(){
//    	txtNomeEdita.setText("");
//    	txtCpfEdita.setText("");
//    	txtDataNascimentoEdita.setValue(null);
//    	txtRgEdita.setText("");
//    	txtEnderecoEdita.setText("");
//    	txtCidadeEdita.setText("");
//    	txtViagemEmpresaEdita.getValueFactory().setValue(0);
//    	txtTelefoneEdita.setText("");
//    }
    
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
//		int viagemEmpresa = Integer.parseInt(txtViagemEmpresaEdita.getText());
//		cliente.setViagemEmpresa(viagemEmpresa);
		cliente.setViagemEmpresa(txtViagemEmpresaEdita.getValue());
		cliente.setTelefone(txtTelefoneEdita.getText());
		cliente.setCodigo(clienteSelecionado.getCodigo());
		cliente.setAtivo(clienteSelecionado.getAtivo());
	}
	
}
