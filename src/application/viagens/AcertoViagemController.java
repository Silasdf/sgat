package application.viagens;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import sgat.entidades.Hotel;
import sgat.entidades.Passageiro;
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
    	demaisDespesas1.setText("0");
    	demaisDespesas2.setText("0");
    	demaisDespesas3.setText("0");
    }
	
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void calcularAcerto(ActionEvent event){
    	carrega();
    }
    
    @FXML
    private void imprimir(ActionEvent event){
    	
    }
    
    @FXML
    private void carrega(){
    	mudarEdicao(false);
    	viagemSelecionada = viagensService.getViagem();
    	List<AcertoGrupoDto> acertos =  viagensService.carregarAcerto(viagensService.getViagem().getCodigo());
    	System.out.println(acertos);
    	String qtdPacotesVendidos = Integer.toString(acertos.size());
    	quantidadePacotesVendidos.setText(qtdPacotesVendidos);
		double valorTotalPoltrona = 0;
		double valorTotalVenda = 0;
		int quantidadeTotalDasPoltronas = 0;
    	for (AcertoGrupoDto umAcerto : acertos){
    		valorTotalPoltrona += umAcerto.getValorPoltrona();
    		valorTotalVenda += umAcerto.getValorVenda();
    		quantidadeTotalDasPoltronas += umAcerto.getQuantidadePassageirosPagantes();
		}
    	String resultadoTotalPacotes = Double.toString(valorTotalVenda + valorTotalPoltrona);
    	valorTotalPacotes.setText(resultadoTotalPacotes);
    	String qtdTotalDasPoltronas = Integer.toString(quantidadeTotalDasPoltronas);
    	quantidadePoltronasVendidos.setText(qtdTotalDasPoltronas);
    	String valorTotalDasPoltronas = Double.toString(valorTotalPoltrona);
    	valorTotalPoltronas.setText(valorTotalDasPoltronas);
    	double valorDespesas = calcularValorDespesas();
    	double valorLucro = (quantidadeTotalDasPoltronas*100) - valorDespesas;
    	String valorLucroStr = Double.toString(valorLucro);
    	valorTotalLucro.setText(valorLucroStr);
    	System.out.println(viagemSelecionada);
    	totalizarHotel(acertos);
    }
    
    private double calcularValorDespesas() {
		double despesa1 = Double.parseDouble(demaisDespesas1.getText());
		double despesa2 = Double.parseDouble(demaisDespesas2.getText());
		double despesa3 = Double.parseDouble(demaisDespesas3.getText());
		return despesa1 + despesa2 + despesa3;
	}

	private void totalizarHotel(List<AcertoGrupoDto> acertos){
    	//Selecionar os hotéis
    	ArrayList<Hotel> hoteis = new ArrayList<Hotel>();
    	for (Passageiro p : viagemSelecionada.getPassageiros()){
    		if (!hoteis.contains(p.getHotel())){
    			hoteis.add(p.getHotel());
    		}
    	};
    	System.out.println("Hotéis Selecionados: " + hoteis);
    	ArrayList<AcertoHotelDto> acertoHoteis = new ArrayList<AcertoHotelDto>();
    	
    	//Totalizar cada hotel
    	for (Hotel h : hoteis){
    		int quantidadePacotesVendidos = 0;
    		double valorTotalDosPacotes = 0;
    		for (AcertoGrupoDto ag : acertos){
    			if (h.equals(ag.getHotel())){
    				quantidadePacotesVendidos += 1;
    				valorTotalDosPacotes += ag.getValorVenda();
    			}
    		}
    		AcertoHotelDto acerto = new AcertoHotelDto(h, quantidadePacotesVendidos, valorTotalDosPacotes);
    		acertoHoteis.add(acerto);
    	};
    	tblAcertoHotel.getItems().setAll(acertoHoteis);
    	System.out.println("Hotéis Totalizados: " + acertoHoteis);
    }
    
    private void mudarEdicao(Boolean novoEstado){
    	quantidadePacotesVendidos.setEditable(novoEstado);
    	valorTotalPacotes.setEditable(novoEstado);
    	quantidadePoltronasVendidos.setEditable(novoEstado);
    	valorTotalPoltronas.setEditable(novoEstado);
    	valorTotalLucro.setEditable(novoEstado);
    }
    
    @FXML
    private void limpar(){
    	quantidadePacotesVendidos.setText("");
    	valorTotalPacotes.setText("");
    	quantidadePoltronasVendidos.setText("");
    	valorTotalPoltronas.setText("");
    	tblAcertoHotel.getItems().clear();
    	demaisDespesas1.setText("0");
    	demaisDespesas2.setText("0");
    	demaisDespesas3.setText("0");
    	valorTotalLucro.setText("");
    }
    
    @FXML
    private void voltar(ActionEvent event){
    	myController.setScreen(ScreensFramework.screen13ID);
    	limpar();
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
