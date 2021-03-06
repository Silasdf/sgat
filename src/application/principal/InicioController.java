package application.principal;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.ResourceBundle;

import application.arquivo.ArquivoDBService;
import application.arquivo.ArquivoService;
import application.clientes.ClientesDBService;
import application.clientes.ClientesService;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import sgat.entidades.Arquivo;
import sgat.entidades.Cliente;

public class InicioController implements Initializable, ControlledScreen {
	
	ScreensController myController;
	
	@FXML
	private MenuItem incluirDocumento;
	
	@FXML
	private TableView<Arquivo> tblArquivo = new TableView<Arquivo>();
	
	@FXML
	private TableColumn<Arquivo, String> clNome = new TableColumn<>("Nome");
	
	private ArquivoService arquivoService;
	
	@FXML
	private TableView<Cliente> tblClientes = new TableView<Cliente>();
	
	@FXML
	private TableColumn<Cliente, String> clNomeAniversariante = new TableColumn<>("Cliente Aniversariante");
	
	@FXML
	private TableColumn<Cliente, String> clDataNascimento = new TableColumn<>("Data de Nascimento");
	
	private ClientesService clientesService;
	
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	arquivoService = ArquivoDBService.getInstance();
    	clientesService = ClientesDBService.getInstance();
    	configuraColunas();
    	buscarDocumentoVigente();
    }
    
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    @FXML
    private void goToCadastroCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen3ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void goToPesquisaCliente(ActionEvent event){
       myController.setScreen(ScreensFramework.screen4ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void cadastroHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen6ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void pesquisaHotel(ActionEvent event){
       myController.setScreen(ScreensFramework.screen7ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void cadastroOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen9ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void pesquisaOnibus(ActionEvent event){
       myController.setScreen(ScreensFramework.screen10ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void cadastroViagem(ActionEvent event){
       myController.setScreen(ScreensFramework.screen12ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    private void pesquisaViagem(ActionEvent event){
       myController.setScreen(ScreensFramework.screen13ID);
       tblClientes.getItems().clear();
    }
    
    @FXML
    public void botaoIncluir(ActionEvent event){
    	if (incluirDocumento()){
    		buscarDocumentoVigente();
    	}
    }

	private void buscarDocumentoVigente() {
//		Arquivo arquivo = new Arquivo();
//		arquivo.setTipo("doc");
//		tblArquivo.getItems().setAll(arquivoService.buscarArquivo(arquivo));
		tblArquivo.getItems().setAll(recuperarDocumentosAtivos());
	}

	private boolean incluirDocumento() {
		List<Arquivo> resultado = recuperarDocumentosAtivos();
		
		FileChooser fc = new FileChooser();
    	
    	fc.setInitialDirectory(new File("//home//silas//Documentos//TCC - 8º Semestre"));
    	fc.getExtensionFilters().addAll(
    			new ExtensionFilter("Files","*.docx","*.doc", "*.pdf"));
    	File selectedFile = fc.showOpenDialog(null);
    	
    	if (selectedFile != null){
    		File file = new File(selectedFile.getAbsolutePath());
    		Arquivo arquivo = new Arquivo();
    		arquivo.setConteudo(file);
    		arquivo.setNome(file.getName());
    		arquivo.setTipo("doc");
    		arquivoService.salvar(arquivo);
    		for (Arquivo a : resultado){
    			arquivoService.apagar(a);
    		}
    		Mensagens.mensagemInformativa("Arquivo cadastrado com sucesso!");
    		return true;
    	} else {
    		System.out.println("Arquivo não é valido");
    	}
    	return false;
	}

	private List<Arquivo> recuperarDocumentosAtivos() {
		Arquivo arq = new Arquivo();
		arq.setTipo("doc");
		List<Arquivo> resultado = arquivoService.buscarArquivo(arq);
		return resultado;
	}
    
	private void configuraColunas() {
		clNome.setCellValueFactory(new PropertyValueFactory<Arquivo, String>("nome"));
		clNomeAniversariante.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nome"));
		clDataNascimento.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dataNascimento"));
	}
	
    @FXML
    public void apagar(){
		Arquivo arquivo = tblArquivo.getSelectionModel().getSelectedItem();
		System.out.println(arquivo);
		if (Mensagens.mensagemConfirmacao("Deseja apagar este cliente?")) {
			arquivoService.apagar(arquivo);
	    	buscarDocumentoVigente();
		}
    }
    
    @FXML
    private void sair(ActionEvent event){
    	System.out.println("Sistema foi fechado!");
    	System.exit(0);
    }
    
    private void buscarAniversariantes(){
    	tblClientes.getItems().setAll(clientesService.buscarAniversariantes());
    }
    
    @FXML
    public void pesquisarAniversariantes(){
//		Cliente cliente = new Cliente();
//		pegaValores(cliente);
		buscarAniversariantes();
		Mensagens.mensagemInformativa("Aniversariantes pesquisados com sucesso!");
    }
    
	// abre uma nova conexão com o banco de dados. Se algum erro for lançado
	// aqui, verifique o erro com atenção e se o banco está rodando
	private Connection conexao() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sgatdb?user=vfturismo&password=vfturismo");
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			if(e instanceof ClassNotFoundException) {
				System.err.println("VERIFIQUE SE O DRIVER DO BANCO DE DADOS ESTÁ NO CLASSPATH");
			} else {
				System.err.println("VERIFIQUE SE O BANCO ESTÁ RODANDO E SE OS DADOS DE CONEXÃO ESTÃO CORRETOS");
			}
			System.exit(0);
			// o sistema deverá sair antes de chegar aqui...
			return null;
		}
	}
    
    //Para Imprimir Documentos prontos
	@FXML
    public void handleImprimirAniversariantes() throws JRException{
    	Connection con = conexao();
    	
//    	File arquivo = new File("application.relatorios/Aniversariantes_Mes.jasper");
//    	if(arquivo.exists()){
//    		//O ARQUIVO EXISTE
//    	}else{
//    		//O ARQUIVO NÃO EXISTE
//    	}
    	
    	URL url = getClass().getResource("/Aniversariantes_Mes.jasper");
    	url.getFile();
    	System.out.println(url.getFile());
    	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);
    	
    	//null: caso não existam filtros
    	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, con);
    	
    	//false: não deixa fechar a aplicação principal
    	JasperViewer jasperViewer = new JasperViewer(jasperPrint, false);
    	jasperViewer.setVisible(true);
    }

}
