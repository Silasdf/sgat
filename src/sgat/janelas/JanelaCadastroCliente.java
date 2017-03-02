package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JanelaCadastroCliente extends Application {
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Cria um botão e inclui o clique
		Button btn_incluir = new Button("Incluir");
		Button btn_sair = new Button("Sair");
		btn_sair.setOnAction(e -> System.exit(0));
		btn_incluir.setOnAction(new EventHandler<ActionEvent>(){
			
			@Override
			// Inclui mensagem no console
			public void handle(ActionEvent event){
				System.out.println("Incluir cadastro cliente");
				
			}
		});
		
		VBox root = new VBox();
		root.getChildren().addAll(btn_incluir, btn_sair);
		Scene scene = new Scene(root,600,400);
		primaryStage.setTitle("Cadastro - Clientes");
		primaryStage.setScene(scene);
		primaryStage.show();
//		try {
//			BorderPane root = new BorderPane();
//			//Criação do botão
//			Button b = new Button("Bem Vindo JavaFX");
////			b.setOnAction(new EventHandler<ActionEvent>(){
////			
////				//Ação do botão, clique
////				public void handle(ActionEvent event){
////					System.out.println("Bem vindo via setOnAction!");
////				}
////			});
//			b.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>(){
//				
//				//Ação do botão, clique outra forma
//				public void handle(ActionEvent event){
//					System.out.println("Bem vindo via addEventHandler!");
//				}
//			});
//			root.setCenter(b);
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
	}
}
