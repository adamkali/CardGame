package src.bin.game_dir.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import src.bin.game_dir.Game;

public class MainUIController extends Application{
    public static final Game gameObject = new Game();
    private static Stage stg;
    private static Scene scene;

    @Override
    public void start(Stage primeStg) throws Exception {
        setStg(primeStg);

        getStg().setOnCloseRequest(e -> guiQuit());

        getStg().setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("initalizeUI.fxml"));
        getStg().setTitle("Game");
        setScene(new Scene(root, 178.0, 257.0));
        getStg().setScene(scene);
        getStg().show();
    }

    public void changeScene(String fxml) throws IOException {
        System.out.println(fxml);
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);

    }

    public static void initialize(String[] args) { launch(args); }

    public static void main(String[] args) {
        launch(args);
    }

    public static void guiQuit() { stg.close(); }

    public static void setStg(Stage stg) { MainUIController.stg = stg; }

    public static Stage getStg() { return stg; }

    public static void setScene( Scene scn ) { MainUIController.scene = scn; }

    public static Scene getScene() { return scene; }

}
