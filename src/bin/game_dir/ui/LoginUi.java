package src.bin.game_dir.ui;

import java.io.IOException;

import src.bin.game_dir.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;

public class LoginUi implements EventHandler<ActionEvent> {

    @FXML
    Button loginButton;
    @FXML
    Button quitButton;
    @FXML
    Button backButton;
    @FXML
    TextField usernameField;
    @FXML
    PasswordField passwordField;
    @FXML
    ImageView futureLogo;
    @FXML
    Label invalidCredentials;

    public void userLogIn() {
        Game temp = MainUIController.gameObject;

        temp.setGameUName(usernameField.getText());
        temp.setGamePWord(passwordField.getText());

        temp.gameLogIn();
        
        if ( temp.getIsLoggedIn() ) {
            MainUIController tempController = new MainUIController();
            MainUIController.guiQuit();
        } else {
            invalidCredentials.setText("Wrong username or password.");
            usernameField.setText("");
            passwordField.setText("");
        }
    }

    public void goBack() throws IOException {
        MainUIController tempObject = new MainUIController();
        tempObject.changeScene("initializeUI.fxml");
    }

    @Override
    public void handle(ActionEvent arg0) {
        try {
            if ( arg0.getSource()==loginButton ) {
                userLogIn();
            } else if ( arg0.getSource() == backButton ) {
                goBack();
            } else if ( arg0.getSource()==quitButton ) {
                MainUIController.guiQuit();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("An error occured: Type " + e.toString());
            alert.setContentText(e.getMessage());
        }
    }

}
