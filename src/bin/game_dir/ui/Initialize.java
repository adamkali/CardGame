package src.bin.game_dir.ui;

import java.io.IOException;

import src.bin.game_dir.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class Initialize implements EventHandler<ActionEvent> {

    @FXML
    Button loginButton;
    @FXML
    Button createAccountButton;
    @FXML
    Button quitButton;

    @Override
    public void handle(ActionEvent arg0) {

        try {
            if (arg0.getSource() == loginButton) {
                guiGameLogIn();
            } else if (arg0.getSource() == createAccountButton) {
                guiGameSignUp();
            } else if (arg0.getSource() == quitButton) {
                MainUIController.guiQuit();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("An error occurred: Type " + e.toString() );
            alert.setContentText(e.getMessage());
        }

    }

    private static void guiGameSignUp() throws IOException {
        MainUIController tempUI = new MainUIController();

        tempUI.changeScene("signUpUI.fxml", 650.0, 450.0);

        Game tempGame = MainUIController.gameObject;
        tempGame.gameCreateAccount();
    }

    private static void guiGameLogIn() throws IOException {
        MainUIController tempUiController = new MainUIController();
        tempUiController.changeScene("loginUi.fxml", 650.0, 450.0);
    }

}
