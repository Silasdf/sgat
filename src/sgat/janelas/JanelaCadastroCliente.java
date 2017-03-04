package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
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
		
		Label lblNome = new Label("Nome:");
		TextField tfNome = new TextField();
		Label lblCpf = new Label("CPF:");
		TextField tfCpf = new TextField();
		
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
		grid.setHgap(10);
		grid.setVgap(12);
		
		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10.0);
		hbButtons.setAlignment(Pos.BOTTOM_RIGHT);
		
		hbButtons.getChildren().addAll(btn_incluir, btn_limpar, btn_pesquisar, btn_sair);
		grid.add(lblNome, 0, 0);
		grid.add(tfNome, 1, 0);
		grid.add(lblCpf, 0, 1);
		grid.add(tfCpf, 1, 1);
		Scene scene = new Scene(grid,600,400); // Aparecer os labels e Textfields
		//Scene scene1 = new Scene(hbButtons,600,400); // Aparecer os botões
		primaryStage.setTitle("Cadastro - Clientes");
		primaryStage.setScene(scene); // Aparecer os labels e Textfields
		//primaryStage.setScene(scene1); // Aparecer os botões
		primaryStage.show();

	}//Fim do método Start
}// Fim da classe JanelaCadastroCliente
