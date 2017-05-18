package application.onibus;

import java.net.URL;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Onibus;

public class EditaOnibusController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridOnibus;
	
	@FXML
	private TextField txtNomeEdita;
	
	@FXML
	private TextField txtValorPorPoltronaEdita;
	
	@FXML
	private TextField txtPlacaOnibusEdita;
	
	@FXML
	private TextField txtOnibusComMultasEdita;
	
	@FXML
	private TextField txtAnoOnibusEdita;
	
	@FXML
	private Spinner<Integer> txtViagensRealizadasEdita;
	
	@FXML
	private TextField txtTelefoneEdita;
	
	private OnibusService onibusService;
	
	private Onibus onibusSelecionado;
	
	   @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	onibusService = OnibusDBService.getInstance();
	    }
	    
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }

	    @FXML
	    private void voltar(ActionEvent event){
	    	myController.setScreen(ScreensFramework.screen10ID);
			limpar();
	    }
	    
	    @FXML
	    private void carrega(){
	    	mudarEdicao(false);
	    	onibusSelecionado = onibusService.getOnibus();
	    	txtNomeEdita.setText(onibusSelecionado.getNome());
	    	txtValorPorPoltronaEdita.setText(onibusSelecionado.getValorPorPoltrona().toString());
	    	txtPlacaOnibusEdita.setText(onibusSelecionado.getPlacaOnibus());
	    	txtOnibusComMultasEdita.setText(onibusSelecionado.getOnibusComMultas());
	    	txtAnoOnibusEdita.setText(onibusSelecionado.getAnoOnibus().toString());
	    	txtViagensRealizadasEdita.getValueFactory().setValue(onibusSelecionado.getViagensRealizadas());
	    	txtTelefoneEdita.setText(onibusSelecionado.getTelefone());
	    	System.out.println(onibusSelecionado);
	    }
	    
	    @FXML
	    private void salvar(ActionEvent event){
			Onibus onibus = new Onibus();
			pegaValores(onibus);
			onibusService.atualizar(onibus);
			Mensagens.mensagemInformativa("Onibus alterado com sucesso!");
	    	mudarEdicao(false);
	    	System.out.println("cliente Editado: " + onibus);
	    }
	    
	    @FXML
	    private void editar(){
	    	mudarEdicao(true);
	    }
	    
	    private void mudarEdicao(Boolean novoEstado){
	    	txtNomeEdita.setEditable(novoEstado);
	    	txtValorPorPoltronaEdita.setEditable(novoEstado);
	    	txtPlacaOnibusEdita.setEditable(novoEstado);
	    	txtOnibusComMultasEdita.setEditable(novoEstado);
	    	txtAnoOnibusEdita.setEditable(novoEstado);
	    	txtViagensRealizadasEdita.setEditable(novoEstado);
	    	txtTelefoneEdita.setEditable(novoEstado);
	    }
	    
	    @FXML
	    private void limpar(){
	    	txtNomeEdita.setText("");
	    	txtValorPorPoltronaEdita.setText("");
	    	txtPlacaOnibusEdita.setText("");
	    	txtOnibusComMultasEdita.setText("");
	    	txtAnoOnibusEdita.setText("");
	    	txtViagensRealizadasEdita.getValueFactory().setValue(0);
	    	txtTelefoneEdita.setText("");
	    }
	    
		// pega os valores entrados pelo usuário e adiciona no objeto onibus
		private void pegaValores(Onibus onibus) {
			onibus.setNome(txtNomeEdita.getText());
			Double valorPoltrona = Double.parseDouble(txtValorPorPoltronaEdita.getText());
			onibus.setValorPorPoltrona(valorPoltrona);
			onibus.setPlacaOnibus(txtPlacaOnibusEdita.getText());
			onibus.setOnibusComMultas(txtOnibusComMultasEdita.getText());
			int anoOnibus = Integer.parseInt(txtAnoOnibusEdita.getText());
			onibus.setAnoOnibus(anoOnibus);
			onibus.setViagensRealizadas(txtViagensRealizadasEdita.getValue());
			onibus.setTelefone(txtTelefoneEdita.getText());
			onibus.setCodigo(onibusSelecionado.getCodigo());
			onibus.setAtivo(onibusSelecionado.getAtivo());
		}

}
