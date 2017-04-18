package application.mensagens;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

public class Mensagens {
	
	static String  fazExe;
	
    public static void mensagemInformativa(String mensagemInformativa){
    	Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    	dialogoInfo.setTitle("SGAT");
    	dialogoInfo.setHeaderText("");
    	dialogoInfo.setContentText(mensagemInformativa);
    	dialogoInfo.showAndWait();
    }
    
    public static void mensagemConfirmacao(String mensagemConfirmacao){
    	
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialogoExe.setTitle("SGAT");
        dialogoExe.setHeaderText("");
        dialogoExe.setContentText(mensagemConfirmacao);
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        dialogoExe.showAndWait().ifPresent(b -> {
            if (b == btnSim) {
                fazExe = "Faz exercício";
            } 
        });

    }
}
