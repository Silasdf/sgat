package application.mensagens;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

public class Mensagens {
	
    public static void mensagemInformativa(String mensagemInformativa){
    	Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
    	dialogoInfo.setTitle("SGAT");
    	dialogoInfo.setHeaderText("");
    	dialogoInfo.setContentText(mensagemInformativa);
    	dialogoInfo.showAndWait();
    }
    
    public static boolean mensagemConfirmacao(String mensagemConfirmacao){
    	
    	boolean resultado = false;
        Alert dialogoExe = new Alert(Alert.AlertType.CONFIRMATION);
        ButtonType btnSim = new ButtonType("Sim");
        ButtonType btnNao = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialogoExe.setTitle("SGAT");
        dialogoExe.setHeaderText("");
        dialogoExe.setContentText(mensagemConfirmacao);
        dialogoExe.getButtonTypes().setAll(btnSim, btnNao);
        
        Optional<ButtonType> result = dialogoExe.showAndWait();
        resultado = (result.get() == btnSim);
        
        return resultado;

    }
}
