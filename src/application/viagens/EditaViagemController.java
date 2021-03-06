package application.viagens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.clientes.ClientesDBService;
import application.clientes.ClientesService;
import application.hoteis.HoteisDBService;
import application.hoteis.HoteisService;
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
import sgat.entidades.Hotel;
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
	private TextField txtValorPoltronaOnibusEdita;
	
	@FXML
	private TextField txtNomePassageiro;
	
	@FXML
	private TextField txtObservacaoOnibus;
	
	@FXML
	private TextField txtNomeHotel;
	
	@FXML
	private TextField txtObservacaoHotel;
	
	@FXML
	private TextField txtValor;
	
	@FXML
	private Spinner<Integer> txtGrupo;
	
	private ViagensService viagensService;
	
	private ClientesService clientesService;
	
	private Cliente clienteSelecionado;
	
	private Viagem viagemSelecionada;
	
	private HoteisService hoteisService;
	
	private Hotel hotelSelecionado;
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	viagensService = ViagensDBService.getInstance();
	    	clientesService = ClientesDBService.getInstance();
	    	hoteisService = HoteisDBService.getInstance();
	    	configuraColunas();
	    	txtNomePassageiro.textProperty().addListener(
	    			(observable, oldValue, newValue) -> {
	    				preencherCampoCliente(newValue);
	    			});
	    	txtNomeHotel.textProperty().addListener(
	    			(observable, oldValue, newValue) -> {
	    				preencherCampoHotel(newValue);
	    			});
	    }
	    
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	    
	    @FXML
	    private void voltar(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen13ID);
	       limpar();
	       limparPassageiro();
	       tblPassageiros.getItems().clear();
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
	    	txtValorPoltronaOnibusEdita.setText(viagemSelecionada.getValorPoltronaOnibus().toString());
	    	tblPassageiros.getItems().setAll(viagemSelecionada.getPassageiros());
	    	System.out.println(viagemSelecionada);
	    }
	    
	    @FXML
	    private void salvar(ActionEvent event){
			Viagem viagem = new Viagem();
			pegaValores(viagem);
			viagensService.atualizar(viagem);
			Mensagens.mensagemInformativa("Viagem alterada com sucesso!");
	    	mudarEdicao(false);
	    	System.out.println("viagem editada: " + viagem);
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
	    	txtValorPoltronaOnibusEdita.setEditable(novoEstado);
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNomeEdita.setText("");
	    	txtDataIdaEdita.setValue(null);
	    	txtDataVoltaEdita.setValue(null);
	    	txtEmbarqueEdita.setText("");
	    	txtHospedagemEdita.setText("");
	    	txtValorPoltronaOnibusEdita.setText("");
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
			Double valorPoltronaOnibus = Double.parseDouble(txtValorPoltronaOnibusEdita.getText());
			viagem.setValorPoltronaOnibus(valorPoltronaOnibus);
			viagem.setCodigo(viagemSelecionada.getCodigo());
			viagem.setAtivo(viagemSelecionada.getAtivo());
			viagem.getPassageiros().addAll(tblPassageiros.getItems());
		}
		
		private Passageiro pegaValoresPassageiro(){
			Passageiro passageiro = new Passageiro();
//			passageiro.setCliente(tblClientes.getSelectionModel().getSelectedItem());
//			clientesService.setClienteSelecionado(passageiro.getCliente());
			passageiro.setCliente(clienteSelecionado);
			passageiro.setObservacaoOnibus(txtObservacaoOnibus.getText());
			passageiro.setHotel(hotelSelecionado);
			passageiro.setObservacaoHotel(txtObservacaoHotel.getText());
			Double valor = Double.parseDouble(txtValor.getText());
			passageiro.setValor(valor);
			passageiro.setGrupo(txtGrupo.getValue());
			return passageiro;
		}
		
		@FXML
		private void selecionaCliente(ActionEvent event){
//			Cliente cliente = new Cliente();
//			cliente.setNome(txtNomePassageiro.getText());
//			tblClientes.getItems().setAll(clientesService.buscarClientes(cliente));
	        myController.setScreen(ScreensFramework.screen15ID);
		}
		
		@FXML
		private void selecionaHotel(ActionEvent event){
	        myController.setScreen(ScreensFramework.screen17ID);
		}
		
		@FXML
		private void incluirPassageiro(ActionEvent event){
			Passageiro passageiro = pegaValoresPassageiro();
			viagemSelecionada.getPassageiros().add(passageiro);
			exibirPassageirosCadastrados();
			limparPassageiro();
		}
		
	    @FXML
		private void incluirNovo(){
	    	limparPassageiro();
	    }

	    private void limparPassageiro(){
	    	txtNomePassageiro.setText("");
	    	clientesService.setClienteSelecionado(null);
	    	txtObservacaoOnibus.setText("");
	    	txtNomeHotel.setText("");
	    	hoteisService.setHotelSelecionado(null);
	    	txtObservacaoHotel.setText("");
	    	txtValor.setText("");
	    	txtGrupo.getValueFactory().setValue(0);
	    }
	    
	    private void preencherCampoCliente(String p){
	    	System.out.println("Cliente selecionado passou aqui!!!");
	    	clienteSelecionado = clientesService.getClienteSelecionado();
	    	if (clienteSelecionado != null){
		    	txtNomePassageiro.setText(clienteSelecionado.getNome());
	    	} else {
		    	txtNomePassageiro.setText("");
	    	}
	    }
	    
	    private void preencherCampoHotel(String h){
	    	System.out.println("Hotel selecionado passou aqui!!!");
	    	hotelSelecionado = hoteisService.getHotelSelecionado();
	    	if (hotelSelecionado != null){
		    	txtNomeHotel.setText(hotelSelecionado.getNome());
	    	} else {
	    		txtNomeHotel.setText("");
	    	}
	    }
	    
		@FXML
		private void alterarPassageiro(ActionEvent event){
			Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
			viagemSelecionada.getPassageiros().remove(passageiro);
			incluirPassageiro(event);
		}
	    
		private void exibirPassageirosCadastrados(){
			tblPassageiros.getItems().setAll(viagemSelecionada.getPassageiros());
		}
		
		@FXML
		private void apagarPassageiro(){
			Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
			System.out.println(passageiro);
			if (Mensagens.mensagemConfirmacao("Deseja apagar este passageiro?")) {
				viagemSelecionada.getPassageiros().remove(passageiro);
				tblPassageiros.getItems().clear();
				exibirPassageirosCadastrados();
			}
		}
		
	    @FXML
	    private void editarPassageiro(ActionEvent event){
	    	limparPassageiro();
	    	carregaPassageiro();
	    }
	    
	    private void carregaPassageiro(){
	    	Passageiro passageiro = tblPassageiros.getSelectionModel().getSelectedItem();
//			txtNomePassageiro.setText(passageiro.getCliente().getNome());
	    	clientesService.setClienteSelecionado(passageiro.getCliente());
			tblClientes.getItems().setAll(passageiro.getCliente());
	    	txtObservacaoOnibus.setText(passageiro.getObservacaoOnibus());
	    	hoteisService.setHotelSelecionado(passageiro.getHotel());
	    	txtObservacaoHotel.setText(passageiro.getObservacaoHotel());
			txtValor.setText(passageiro.getValor().toString());
	    	txtGrupo.getValueFactory().setValue(passageiro.getGrupo());
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
