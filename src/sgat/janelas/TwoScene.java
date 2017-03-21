package sgat.janelas;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class TwoScene extends Application {
	
	Button btnscene1, btnscene2;
    Label lblscene1, lblscene2;
    FlowPane pane1, pane2;
    Scene scene1, scene2, scenePesquisaCliente, sceneCadastroCliente;
    Stage thestage;
    
    public void start(Stage primaryStage) {
        
        thestage=primaryStage;
        //can now use the stage in other methods
       
        montaScene1();
        
        montaScene2();
        
       scenePesquisaCliente = ConstruirJanelaPesquisaCliente.montaJanelaPesquisa();
       
       sceneCadastroCliente = ConstruirJanelaCadastroCliente.montaJanelaCadastro();
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene1);
        primaryStage.show();
        

    }



	private void montaScene2() {
		btnscene2=new Button("Click to go back to First Scene");
        lblscene2=new Label("Scene 2");
        btnscene2.setOnAction(e-> ButtonClicked(e));
        
        pane2=new FlowPane();
        pane2.setVgap(10);

        pane2.setStyle("-fx-background-color: red;-fx-padding: 10px;");

        //add everything to panes
        
        pane2.getChildren().addAll(lblscene2, btnscene2);
        
        //make 2 scenes from 2 panes

        
        scene2 = new Scene(pane2, 200, 100);
	}

	private void montaScene1() {
		//make things to put on panes
        btnscene1=new Button("Click to go to Other Scene");
        lblscene1=new Label("Scene 1");
        btnscene1.setOnAction(e-> ButtonClicked(e));

        //make 1 Panes
        pane1=new FlowPane();
        pane1.setVgap(10);
        
        //set background color of each Pane
        pane1.setStyle("-fx-background-color: tan;-fx-padding: 10px;");
        
        pane1.getChildren().addAll(lblscene1, btnscene1);
        
        scene1 = new Scene(pane1, 200, 100);
	}
    
    public void ButtonClicked(ActionEvent e)
    {
        if (e.getSource()==btnscene1)
            thestage.setScene(sceneCadastroCliente);
        else
            thestage.setScene(scene1);
    }
    
	public static void main(String[] args){
		launch(args);
	} // Fim do método main

}
