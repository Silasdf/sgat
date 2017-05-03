package application.viagens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sgat.entidades.Viagem;

public class EditaViagemController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
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
	
	private ViagensService viagensService;
	
	private Viagem viagemSelecionada;
	
	 @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	viagensService = ViagensDBService.getInstance();
	    }
	    
	    public void setScreenParent(ScreensController screenParent){
	        myController = screenParent;
	    }
	    
	    @FXML
	    private void voltar(ActionEvent event){
	       myController.setScreen(ScreensFramework.screen13ID);
	       limpar();
	    }
	    
	    @FXML
	    private void carrega(){
	    	mudarEdicao(false);
	    	viagemSelecionada = viagensService.getViagem();
	    	txtNomeEdita.setText(viagemSelecionada.getNome());
	    	txtDataIdaEdita.setValue(viagemSelecionada.getDataIda());
	    	txtDataVoltaEdita.setValue(viagemSelecionada.getDataVolta());
	    	txtEmbarqueEdita.setText(viagemSelecionada.getHospedagem());
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

}
