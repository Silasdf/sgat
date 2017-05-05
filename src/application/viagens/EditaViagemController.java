package application.viagens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.clientes.ClientesDBService;
import application.clientes.ClientesService;
import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import sgat.entidades.Cliente;
import sgat.entidades.Passageiro;
import sgat.entidades.Viagem;

public class EditaViagemController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private TableView<Cliente> tblClientes = new TableView<Cliente>();
	
	@FXML
	private TableColumn<Cliente, String> clNome = new TableColumn<>("Nome");
	
	@FXML
	private TableView<Passageiro> tblPassageiros = new TableView<Passageiro>();
	
	@FXML
	private TableColumn<Passageiro, String> clNomePassageiro = new TableColumn<>("Passageiro");
	
	@FXML
	private TableColumn<Passageiro, String> clGrupo = new TableColumn<>("Grupo");
	
	@FXML
	private GridPane gridViagem;
	
	@FXML
	private TextField txtNomeEdita;
	
	@FXML
	private DatePicker txtDataIdaEdita;
	
	@FXML
	private DatePicker txtDataVoltaEdita;
	
	@FXML
	private TextField txtEmbarqueEdita;
	
	@FXML
	private TextField txtHospedagemEdita;
	
	@FXML
	private TextField txtNomePassageiro;
	
	@FXML
	private TextField txtObservacaoOnibus;
	
	@FXML
	private TextField txtObservacaoHotel;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private Spinner<Integer> txtGrupo;
	
	private ViagensService viagensService;
	
	private ClientesService clientesService;
	
	private Viagem viagemSelecionada;
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	viagensService = ViagensDBService.getInstance();
	    	clientesService = ClientesDBService.getInstance();
	    	configuraColunas();
	    }
	    
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	    
	    @FXML
	    private void voltar(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen13ID);
	       limpar();
	       limparPassageiro();
	    }
	    
	    @FXML
	    private void carrega(){
	    	mudarEdicao(false);
	    	viagemSelecionada = viagensService.getViagem();
	    	txtNomeEdita.setText(viagemSelecionada.getNome());
	    	txtDataIdaEdita.setValue(viagemSelecionada.getDataIda());
	    	txtDataVoltaEdita.setValue(viagemSelecionada.getDataVolta());
	    	txtEmbarqueEdita.setText(viagemSelecionada.getEmbarque());
	    	txtHospedagemEdita.setText(viagemSelecionada.getHospedagem());
	    	System.out.println(viagemSelecionada);
	    }
	    
	    @FXML
	    private void salvar(ActionEvent event){
			Viagem viagem = new Viagem();
			pegaValores(viagem);
			viagensService.atualizar(viagem);
			Mensagens.mensagemInformativa("Viagem alterada com sucesso!");
	    	mudarEdicao(false);
	    	System.out.println("cliente Editado: " + viagem);
	    }
	    
	    @FXML
	    private void editar(){
	    	mudarEdicao(true);

	    }
	    
	    private void mudarEdicao(Boolean novoEstado){
	    	txtNomeEdita.setEditable(novoEstado);
	    	txtDataIdaEdita.setEditable(novoEstado);
	    	txtDataVoltaEdita.setEditable(novoEstado);
	    	txtEmbarqueEdita.setEditable(novoEstado);
	    	txtHospedagemEdita.setEditable(novoEstado);
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNomeEdita.setText("");
	    	txtDataIdaEdita.setValue(null);
	    	txtDataVoltaEdita.setValue(null);
	    	txtEmbarqueEdita.setText("");
	    	txtHospedagemEdita.setText("");
	    }
	    
		// pega os valores entrados pelo usuário e adiciona no objeto viagem
		private void pegaValores(Viagem viagem) {
			viagem.setNome(txtNomeEdita.getText());
			LocalDate dataIda = txtDataIdaEdita.getValue();
			viagem.setDataIda(dataIda);
			LocalDate dataVolta = txtDataVoltaEdita.getValue();
			viagem.setDataVolta(dataVolta);
			viagem.setEmbarque(txtEmbarqueEdita.getText());
			viagem.setHospedagem(txtHospedagemEdita.getText());
			viagem.setCodigo(viagemSelecionada.getCodigo());
			viagem.setAtivo(viagemSelecionada.getAtivo());
		}
		
		private Passageiro pegaValoresPassageiro(){
			Passageiro passageiro = new Passageiro();
			passageiro.setCliente(tblClientes.getSelectionModel().getSelectedItem());
			passageiro.setObservacaoOnibus(txtObservacaoOnibus.getText());
			passageiro.setObservacaoHotel(txtObservacaoHotel.getText());
			Double valor = Double.parseDouble(txtValor.getText());
			passageiro.setValor(valor);
			passageiro.setGrupo(txtGrupo.getValue());
			return passageiro;
		}
		
		@FXML
		private void incluirPassageiro(ActionEvent event){
			Passageiro passageiro = pegaValoresPassageiro();
			viagemSelecionada.getPassageiros().add(passageiro);
			exibirPassageirosCadastrados();
		}
		
		@FXML
		private void pesquisarCliente(ActionEvent event){
			Cliente cliente = new Cliente();
			cliente.setNome(txtNomePassageiro.getText());
			tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
		}
		
		private void exibirPassageirosCadastrados(){
			tblPassageiros.getItems().setAll(viagemSelecionada.getPassageiros());
		}
		
	    @FXML
		private void incluirNovo(){
	    	limparPassageiro();
	    }

	    private void limparPassageiro(){
	    	txtNomePassageiro.setText("");
	    	tblClientes.getItems().clear();
	    	txtObservacaoOnibus.setText("");
	    	txtObservacaoHotel.setText("");
	    	txtValor.setText("");
	    	txtGrupo.getValueFactory().setValue(0);
	    }
	    
	    @FXML
	    private void editarPassageiro(ActionEvent event){
	    	limparPassageiro();
	    	carregaPassageiro();
	    }
	    
	    private void carregaPassageiro(){
	    	Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
			txtNomePassageiro.setText(passageiro.getCliente().getNome());
	    	tblClientes.getItems().setAll(passageiro.getCliente());
	    	txtObservacaoOnibus.setText(passageiro.getObservacaoOnibus());
	    	txtObservacaoHotel.setText(passageiro.getObservacaoHotel());
			txtValor.setText(passageiro.getValor().toString());
	    	txtGrupo.getValueFactory().setValue(passageiro.getGrupo());
	    }
	    
		@FXML
		private void alterarPassageiro(ActionEvent event){
			Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
			viagemSelecionada.getPassageiros().remove(passageiro);
			incluirPassageiro(event);
		}
		
		@FXML
		private void apagarPassageiro(){
			Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
			System.out.println(passageiro);
			if (Mensagens.mensagemConfirmacao("Deseja apagar este passageiro?")) {
				viagemSelecionada.getPassageiros().remove(passageiro);
				tblPassageiros.getItems().clear();
			}
		}
		
		private void configuraColunas() {
			clNome.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
			clNomePassageiro.setCellValueFactory(new Callback <TableColumn.CellDataFeatures<Passageiro, String>, ObservableValue<String>>(){
				public ObservableValue<String> call(TableColumn.CellDataFeatures<Passageiro, String> c){
					String res = c.getValue().getCliente().getNome() ;
					return new SimpleStringProperty(res);
				}
			});
			clGrupo.setCellValueFactory(new PropertyValueFactory<Passageiro, String>("grupo"));
		}
		
}
