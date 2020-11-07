package GUI.screens.loginScreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginScreenController{

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @FXML
    Button signupButton;

    @FXML
    Button developerToolsButton;


    public void signupButtonPressed(ActionEvent event) throws IOException
    {
        Parent root_signup = FXMLLoader.load(getClass().getResource("../signupScreen/signupScreen.fxml"));
        Scene signupScene = new Scene(root_signup);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(signupScene);
        window.show();
    }

    public void validateLoginDetails()
    {
        usernameField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        passwordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        if(usernameField.getText().trim().isEmpty()==true)
        {
            usernameField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

            usernameField.setPromptText("Username can not be blank");
        }

        if(passwordField.getText().isEmpty()==true)
        {
            passwordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            passwordField.setPromptText("Password can not be blank");
        }
    }
}
