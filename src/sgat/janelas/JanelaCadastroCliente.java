package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JanelaCadastroCliente extends Application {
	
	public static void main(String[] args){
		launch(args);
	} // Fim do método main
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Cria um botão e inclui o clique
		Button btn_incluir = new Button("Incluir");
		Button btn_limpar = new Button("Limpar");
		Button btn_pesquisar = new Button("Pesquisar");
		Button btn_sair = new Button("Sair");
		
		// Cria Labels.
		Label lblNome = new Label("Nome:");
		Label lblCpf = new Label("CPF:");
		Label lblDataNascimento = new Label("Data de Nascimento:");
		Label lblRg = new Label("R.G:");
		Label lblEndereco = new Label("Endereço:");
		Label lblCidade = new Label("Cidade:");
		Label lblViagPelaEmp = new Label("Viagens Pela Empresa:");
		
		// Cria TextFields
		TextField tfNome = new TextField();
		TextField tfCpf = new TextField();
		TextField tfDataNascimento = new TextField();
		TextField tfRg = new TextField();
		TextField tfEndereco = new TextField();
		TextField tfCidade = new TextField();
		TextField tfViagPelaEmp = new TextField();
		
		btn_incluir.setOnAction(new EventHandler<ActionEvent>(){
			
			// Inclui mensagem no console
			public void handle(ActionEvent event){
				System.out.println("Incluir cadastro cliente");
				
			}
		});
		
		btn_limpar.setOnAction(new EventHandler<ActionEvent>(){
			
			// Limpar mensagem no console
			public void handle(ActionEvent event){
				System.out.println("Limpar cadastro cliente");
				
			}
		});
		
		btn_pesquisar.setOnAction(new EventHandler<ActionEvent>(){
			
			// Pesquisar mensagem no console
			public void handle(ActionEvent event){
				System.out.println("Pesquisar cadastro cliente");
				
			}
		});
		
		btn_sair.setOnAction(e -> {
			System.out.println("Sair da aplicação");
			System.exit(0);
		});
		
		GridPane grid = new GridPane();
		grid.setHgap(5); // Separação entre as linhas horizontalmente
		grid.setVgap(12); // Separação entre as linhas verticalmente
		grid.setPadding(new Insets(10, 10, 10, 10)); // Espaçamento nas bordas
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10.0);
//		hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
		
		hbButtons.getChildren().addAll(btn_incluir, btn_limpar, btn_pesquisar, btn_sair);
		// Inclusão labels e taxtfields no grid
		grid.add(lblNome, 0, 0);
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
		grid.add(lblViagPelaEmp, 0, 6);
		grid.add(tfViagPelaEmp, 1, 6);
		// Inclusão dos botões no grid
		grid.add(hbButtons, 7, 7, 7, 1);
		Scene scene = new Scene(grid,675,400); // Aparecer os labels e Textfields
		primaryStage.setTitle("Cadastro - Clientes");
		primaryStage.getIcons().add(new Image("imagens/S.png"));
		primaryStage.setScene(scene); // Aparecer os labels e Textfields
		primaryStage.show();

	}//Fim do método Start
}// Fim da classe JanelaCadastroCliente
