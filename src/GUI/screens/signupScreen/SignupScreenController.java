package GUI.screens.signupScreen;

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

public class SignupScreenController {
    @FXML
    Button loginButton;

    @FXML
    Button signupButton;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField fullnameField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField confirmPasswordField;

    public void loginButtonPressed(ActionEvent event) throws IOException
    {
        Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
        Scene loginScene = new Scene(root_login);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void validateSignupDetails(){
        usernameField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        fullnameField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        emailField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        passwordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        confirmPasswordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        if(usernameField.getText().trim().isEmpty()==true)
        {
            usernameField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

            usernameField.setPromptText("Username can not be blank");
        }
        if(fullnameField.getText().trim().isEmpty()==true)
        {
            fullnameField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            fullnameField.setPromptText("Name can not be blank");
        }
        if(emailField.getText().trim().isEmpty()==true)
        {
            emailField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            emailField.setPromptText("Email can not be blank");
        }
        if(passwordField.getText().isEmpty()==true)
        {
            passwordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            passwordField.setPromptText("Password can not be blank");
        }
        if(confirmPasswordField.getText().isEmpty()==true)
        {
            confirmPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            confirmPasswordField.setPromptText("Password can not be blank");
        }
        if(passwordField.getText() != confirmPasswordField.getText())
        {
            passwordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            passwordField.clear();

            confirmPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            confirmPasswordField.clear();
            passwordField.setPromptText("Invalid Password");
            confirmPasswordField.setPromptText("Invalid Password");
        }
    }
}
