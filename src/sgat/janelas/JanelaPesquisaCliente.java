package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
//import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sgat.entidades.Cliente;

public class JanelaPesquisaCliente extends Application {
	
	public static void main(String[] args){
		launch(args);
	} // Fim do método main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Cria um botão e inclui o clique
		Button btn_pesquisar = new Button("Pesquisar");
		Button btn_limpar = new Button("Limpar");
		Button btn_voltar = new Button("Voltar");
		//Button btn_sair = new Button("Sair");
		
		// Cria Labels.
		Label lblNome = new Label("Nome:");
		Label lblCpf = new Label("CPF:");
		Label lblDataNascimento = new Label("Data de Nascimento:");
		Label lblRg = new Label("R.G:");
		Label lblEndereco = new Label("Endereço:");
		Label lblCidade = new Label("Cidade:");
		Label lblViagemEmpresa = new Label("Viagens Pela Empresa:");
		
		// Cria TextFields
		TextField tfNome = new TextField();
		TextField tfCpf = new TextField();
		DatePicker tfDataNascimento = new DatePicker();
		TextField tfRg = new TextField();
		TextField tfEndereco = new TextField();
		TextField tfCidade = new TextField();
		TextField tfViagemEmpresa = new TextField();
		
		btn_pesquisar.setOnAction(new EventHandler<ActionEvent>(){
			
			// Pesquisar, exibe mensagem no console após ocorrer uma ação
			public void handle(ActionEvent event){
				System.out.println("Pesquisa por clientes cadastrados");
				
			}
		});
		
btn_limpar.setOnAction(new EventHandler<ActionEvent>(){
			
			// Limpar mensagem no console
			public void handle(ActionEvent event){
				System.out.println("Limpar cadastro cliente");
				
				limparTela();
				
			}

			private void limparTela() {

				tfNome.setText("");
				tfCpf.setText("");
				tfDataNascimento.setValue(null);
				tfRg.setText("");
				tfEndereco.setText("");
				tfCidade.setText("");
				tfViagemEmpresa.setText("");
				
			}
		});
		
		btn_voltar.setOnAction(new EventHandler<ActionEvent>(){
			
			// Voltar, exibe mensagem no console após ocorrer uma ação
			public void handle(ActionEvent event){
				System.out.println("Voltar para a página inicial");
				
			}
		});
		
//		btn_sair.setOnAction(e -> {
//			System.out.println("Sair da aplicação");
//			System.exit(0);
//		});
		
		GridPane grid = new GridPane();
		grid.setHgap(5); // Separação entre as linhas horizontalmente
		grid.setVgap(12); // Separação entre as linhas verticalmente
		grid.setPadding(new Insets(10, 10, 10, 10)); // Espaçamento nas bordas
		
		ColumnConstraints coluna01 = new ColumnConstraints(150);
		ColumnConstraints coluna02 = new ColumnConstraints(150, 300, 400);
		//ColumnConstraints coluna03 = new ColumnConstraints(10);
		
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10.0);
//		hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
		
		VBox tabelas = new VBox();

		TableView<Cliente> tabela = new TableView<>();
		
		hbButtons.getChildren().addAll(btn_pesquisar, btn_limpar, btn_voltar);
		tabelas.getChildren().add(tabela);
		grid.getColumnConstraints().addAll(coluna01, coluna02);
		
		// Inclusão labels e taxtfields no grid
		grid.add(lblNome, 0, 0); // Coluna e Linha
		grid.add(tfNome, 1, 0);
		grid.add(lblCpf, 0, 1);
		grid.add(tfCpf, 1, 1);
		grid.add(lblDataNascimento, 0, 2);
		grid.add(tfDataNascimento, 1, 2);
		grid.add(lblRg, 0, 3);
		grid.add(tfRg, 1, 3);
		grid.add(lblEndereco, 0, 4);
		grid.add(tfEndereco, 1, 4);
		grid.add(lblCidade, 0, 5);
		grid.add(tfCidade, 1, 5);
		grid.add(lblViagemEmpresa, 0, 6);
		grid.add(tfViagemEmpresa, 1, 6);

		grid.add(hbButtons, 2, 7);// Inclusão dos botões no grid
		
		grid.add(tabela, 0, 8, 8, 2);

		Scene scene = new Scene(grid,1200,600); // Aparecer os labels e Textfields largura e altura
		primaryStage.setTitle("Pesquisa - Clientes"); // Titulo da janela
		primaryStage.getIcons().add(new Image("imagens/S_1.png")); // Icone na janela
		primaryStage.setScene(scene); // Aparecer os labels e Textfields
		primaryStage.show();
		
//		TableView<Cliente> tabela = new TableView<>();
		
//		VBox tabelas = new VBox();
//		tabelas.setAlignment(Pos.BOTTOM_RIGHT);		
		
//		grid.getChildren().add(tabela);


	}//Fim do método Start
}// Fim da classe JanelaCadastroCliente
