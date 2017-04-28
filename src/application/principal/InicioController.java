package application.principal;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import application.arquivo.ArquivoDBService;
import application.arquivo.ArquivoService;
import application.mensagens.Mensagens;
import framework.ControlledScreen;
import framework.ScreensController;
import framework.ScreensFramework;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import sgat.entidades.Arquivo;

public class InicioController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private MenuItem incluirDocumento;
	
	@FXML
	private TableView<Arquivo> tblArquivo = new TableView<Arquivo>();
	
	@FXML
	private TableColumn<Arquivo, String> clNome = new TableColumn<>("Nome");
	
	private ArquivoService arquivoService;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	arquivoService = ArquivoDBService.getInstance();
    	configuraColunas();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void goToCadastroCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen3ID);
    }
    
    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
    }
    
    @FXML
    private void cadastroHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen6ID);
    }
    
    @FXML
    private void pesquisaHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen7ID);
    }
    
    @FXML
    private void cadastroOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen9ID);
    }
    
    @FXML
    private void pesquisaOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen10ID);
    }
    
    @FXML
    public void botaoIncluir(ActionEvent event){
    	FileChooser fc = new FileChooser();
//    	File file = new File(filename);
    	
    	fc.setInitialDirectory(new File("//home//silas//Documentos//TCC - 8º Semestre"));
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("Files","*.docx","*.doc", "*.pdf"));
    	File selectedFile = fc.showOpenDialog(null);
//    	File selectedFile = fc.showSaveDialog(window);
    	
    	if (selectedFile != null){
//    		listaDocumento.getItems().addAll(selectedFile.getAbsolutePath());
    		File file = new File(selectedFile.getAbsolutePath());
    		Arquivo arquivo = new Arquivo();
    		arquivo.setConteudo(file);
    		arquivo.setNome(file.getName());
    		arquivo.setTipo("docs");
    		arquivoService.salvar(arquivo);
    		Mensagens.mensagemInformativa("Arquivo cadastrado com sucesso!");
//    	    Files.copy(file.toPath(), selectedFile.toPath());
    	} else {
    		System.out.println("Arquivo não é valido");
    	}
    }
    
    @FXML
    public void pesquisar(){
       	FileChooser fc = new FileChooser();
//    	File file = new File(filename);
    	
    	fc.setInitialDirectory(new File("//home//silas//Documentos//TCC - 8º Semestre"));
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("Files","*.docx","*.doc", "*.pdf"));
    	File selectedFile = fc.showOpenDialog(null);
//    	File selectedFile = fc.showSaveDialog(window);
    	
    	if (selectedFile != null){
//    		listaDocumento.getItems().addAll(selectedFile.getAbsolutePath());
    		File file = new File(selectedFile.getAbsolutePath());
    		Arquivo arquivo = new Arquivo();
    		arquivo.setConteudo(file);
    		arquivo.setNome(file.getName());
    		arquivo.setTipo("docs");
        	buscarArquivo(arquivo);
    		Mensagens.mensagemInformativa("Cliente pesquisado com sucesso!");
//    	    Files.copy(file.toPath(), selectedFile.toPath());
    	} else {
    		System.out.println("Arquivo não é valido");
    	}
    }
    
    private void buscarArquivo(Arquivo arquivo) {
		tblArquivo.getItems().setAll(arquivoService.buscarArquivo(arquivo));
    }
    
	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Arquivo, String>("nome"));
	}
	
    @FXML
    public void apagar(){
		Arquivo arquivo = tblArquivo.getSelectionModel().getSelectedItem();
		System.out.println(arquivo);
		if (Mensagens.mensagemConfirmacao("Deseja apagar este cliente?")) {
			arquivoService.apagar(arquivo);
	    	tblArquivo.getItems().clear();
		}
    }
    
    @FXML
    private void sair(ActionEvent event){
    	System.out.println("Sistema foi fechado!");
    	System.exit(0);
    }

}
