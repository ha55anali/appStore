package GUI.screens.devLoginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DevLoginScreenController {

    @FXML
    Button viewStoreAsUserButton;

    @FXML
    Button devSignupButton;

    public void viewStoreAsUserButtonPressed(ActionEvent event) throws IOException {
        Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
        Scene loginScene = new Scene(root_login);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void devSignupButtonPressed(ActionEvent event) throws IOException {
        Parent root_devSignupScreen = FXMLLoader.load(getClass().getResource("../devSignupScreen/devSignupScreen.fxml"));
        Scene scene_devSignupScreen = new Scene(root_devSignupScreen);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene_devSignupScreen);
        window.show();
    }
}
