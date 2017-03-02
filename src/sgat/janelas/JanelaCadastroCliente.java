package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class JanelaCadastroCliente extends Application {
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		// Cria um botão e inclui o clique
		Button btn_incluir = new Button("Incluir");
		Button btn_limpar = new Button("Limpar");
		Button btn_pesquisar = new Button("Pesquisar");
		Button btn_sair = new Button("Sair");
		
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
		
		HBox root = new HBox();
		root.setSpacing(10.0);
		root.setAlignment(Pos.BOTTOM_RIGHT);
		root.getChildren().addAll(btn_incluir, btn_limpar, btn_pesquisar, btn_sair);
		Scene scene = new Scene(root,600,400);
		primaryStage.setTitle("Cadastro - Clientes");
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
