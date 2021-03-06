package framework;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
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
    
    public static String screen9ID = "screen9";
    public static String screen9File = "../application/onibus/CadastroOnibus.fxml";
    
    public static String screen10ID = "screen10";
    public static String screen10File = "../application/onibus/PesquisaOnibus.fxml";
    
    public static String screen11ID = "screen11";
    public static String screen11File = "../application/onibus/EditaOnibus.fxml";
    
    public static String screen12ID = "screen12";
    public static String screen12File = "../application/viagens/CadastroViagem.fxml";
    
    public static String screen13ID = "screen13";
    public static String screen13File = "../application/viagens/PesquisaViagem.fxml";
    
    public static String screen14ID = "screen14";
    public static String screen14File = "../application/viagens/EditaViagem.fxml";
    
    public static String screen15ID = "screen15";
    public static String screen15File = "../application/clientes/SelecionaCliente.fxml";
    
    public static String screen16ID = "screen16";
    public static String screen16File = "../application/viagens/AcertoViagem.fxml";
    
    public static String screen17ID = "screen17";
    public static String screen17File = "../application/hoteis/SelecionaHotel.fxml";

    
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
        mainContainer.loadScreen(ScreensFramework.screen9ID, ScreensFramework.screen9File);
        mainContainer.loadScreen(ScreensFramework.screen10ID, ScreensFramework.screen10File);
        mainContainer.loadScreen(ScreensFramework.screen11ID, ScreensFramework.screen11File);
        mainContainer.loadScreen(ScreensFramework.screen12ID, ScreensFramework.screen12File);
        mainContainer.loadScreen(ScreensFramework.screen13ID, ScreensFramework.screen13File);
        mainContainer.loadScreen(ScreensFramework.screen14ID, ScreensFramework.screen14File);
        mainContainer.loadScreen(ScreensFramework.screen15ID, ScreensFramework.screen15File);
        mainContainer.loadScreen(ScreensFramework.screen16ID, ScreensFramework.screen16File);
        mainContainer.loadScreen(ScreensFramework.screen17ID, ScreensFramework.screen17File);
        
        mainContainer.setScreen(ScreensFramework.screen1ID);
        
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        scene.setFill(Color.web("dae7ed"));
		primaryStage.getIcons().add(new Image("imagens/S_1.png"));
		primaryStage.setTitle("SGAT");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
