package framework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ScreensFramework extends Application{
	
	public static String screen1ID = "main";
    public static String screen1File = "../application/principal/Login.fxml";
    
    public static String screen2ID = "screen2";
    public static String screen2File = "../application/principal/Inicio.fxml";
    
    public static String screen3ID = "screen3";
    public static String screen3File = "../application/clientes/CadastroCliente.fxml";
    
    public static String screen4ID = "screen4";
    public static String screen4File = "../application/clientes/PesquisaCliente.fxml";
    
    public static String screen5ID = "screen5";
    public static String screen5File = "../application/clientes/EditaCliente.fxml";
    
    public static String screen6ID = "screen6";
    public static String screen6File = "../application/hoteis/CadastroHotel.fxml";
    
    public static String screen7ID = "screen7";
    public static String screen7File = "../application/hoteis/PesquisaHotel.fxml";
    
    public static String screen8ID = "screen8";
    public static String screen8File = "../application/hoteis/EditaHotel.fxml";
    
    @Override
    public void start(Stage primaryStage) {
    	
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen(ScreensFramework.screen1ID, ScreensFramework.screen1File);
        mainContainer.loadScreen(ScreensFramework.screen2ID, ScreensFramework.screen2File);
        mainContainer.loadScreen(ScreensFramework.screen3ID, ScreensFramework.screen3File);
        mainContainer.loadScreen(ScreensFramework.screen4ID, ScreensFramework.screen4File);
        mainContainer.loadScreen(ScreensFramework.screen5ID, ScreensFramework.screen5File);
        mainContainer.loadScreen(ScreensFramework.screen6ID, ScreensFramework.screen6File);
        mainContainer.loadScreen(ScreensFramework.screen7ID, ScreensFramework.screen7File);
        mainContainer.loadScreen(ScreensFramework.screen8ID, ScreensFramework.screen8File);
        
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
		primaryStage.getIcons().add(new Image("imagens/S_1.png"));
		primaryStage.setTitle("SGAT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
