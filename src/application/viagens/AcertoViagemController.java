package application.viagens;

import java.net.URL;
import java.util.ResourceBundle;

import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import sgat.entidades.Viagem;

public class AcertoViagemController implements Initializable, ControlledScreen{
	
	ScreensController myController;
	
	@FXML
	private GridPane gridAcerto;
	
	@FXML
	private TableView<AcertoHotelDto> tblAcertoHotel = new TableView<AcertoHotelDto>();
	
	@FXML
	private TableColumn<AcertoHotelDto, String> clHotel = new TableColumn<>("Hotel");
	
	@FXML
	private TableColumn<AcertoHotelDto, Integer> clQuantidadePacotesVendidos = new TableColumn<>("QuantidadePacotesVendidos");
	
	@FXML
	private TableColumn<AcertoHotelDto, Double> clValorTotalPacotes = new TableColumn<>("ValorTotalPacotes");
	
	@FXML
	private TextField quantidadePacotesVendidos;
	
	@FXML
	private TextField valorTotalPacotes;
	
	@FXML
	private TextField quantidadePoltronasVendidos;
	
	@FXML
	private TextField valorTotalPoltronas;
	
	@FXML
	private TextField demaisDespesas1;
	
	@FXML
	private TextField demaisDespesas2;
	
	@FXML
	private TextField demaisDespesas3;
	
	@FXML
	private TextField valorTotalLucro;
	
	private ViagensService viagensService;
	
	private Viagem viagemSelecionada;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	viagensService = ViagensDBService.getInstance();
    	configuraColunas();
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void calcularAcerto(ActionEvent event){
    	
    }
    
    @FXML
    private void imprimir(ActionEvent event){
    	
    }
    
    @FXML
    private void carrega(){
    	viagemSelecionada = viagensService.getViagem();
//    	quantidadePacotesVendidos.setText(viagemSelecionada.getNome());
//    	valorTotalPacotes.setText(viagemSelecionada.getDataIda());
//    	txtDataVoltaEdita.setValue(viagemSelecionada.getDataVolta());
//    	txtEmbarqueEdita.setText(viagemSelecionada.getEmbarque());
//    	txtHospedagemEdita.setText(viagemSelecionada.getHospedagem());
//    	tblPassageiros.getItems().setAll(viagemSelecionada.getPassageiros());
    	System.out.println(viagemSelecionada);
    }
    
    @FXML
    private void limpar(ActionEvent event){
    	
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen13ID);
    }
    
	private void configuraColunas() {
		clValorTotalPacotes.setCellValueFactory(new PropertyValueFactory<AcertoHotelDto, Double>("valorTotalDosPacotes"));
		clHotel.setCellValueFactory(new Callback <TableColumn.CellDataFeatures<AcertoHotelDto, String>, ObservableValue<String>>(){
			public ObservableValue<String> call(TableColumn.CellDataFeatures<AcertoHotelDto, String> c){
				String res = c.getValue().getHotel().getNome() ;
				return new SimpleStringProperty(res);
			}
		});
		clQuantidadePacotesVendidos.setCellValueFactory(new PropertyValueFactory<AcertoHotelDto, Integer>("quantidadePacotesVendidos"));
	}
    
}
