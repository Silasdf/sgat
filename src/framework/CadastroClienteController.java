package framework;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import sgat.entidades.Cliente;

public class CadastroClienteController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
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
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen3ID);
    }
    
//    @FXML
//    private void goToScreen3(ActionEvent event){
//       myController.setScreen(ScreensFramework.screen3ID);
//    }
    
    @FXML
    private void salvar(ActionEvent event){
    	System.out.println("Cliquei em salvar!");
		Cliente cliente = new Cliente();
		pegaValores(cliente);
		clientesService.salvar(cliente);
		atualizaDadosTabela();
    }
    
	// pega os valores entrados pelo usuário e adiciona no objeto conta
	private void pegaValores(Cliente cliente) {
		cliente.setNome(txtNome.getText());
		cliente.setCpf(txtCpf.getText());
		LocalDate data = txtDataNascimento.getValue();
		cliente.setDataNascimento(data);
//		cliente.setDataNascimento(txtDataNascimento.getValue());
		cliente.setRg(txtRg.getText());
		cliente.setEndereco(txtEndereco.getText());
		cliente.setCidade(txtCidade.getText());
		cliente.setViagemEmpresa(txtViagemEmpresa.getText());
//		c.setConcessionaria(txtConsc.getText());
//		c.setDescricao(txtDesc.getText());
//		c.setDataVencimento(dataSelecionada());
	}
    
	// chamado quando acontece alguma operação de atualização dos dados
	private void atualizaDadosTabela() {
//		tblContas.getItems().setAll(service.buscarTodas());
//		limpar();
	}

}


