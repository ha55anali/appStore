package GUI.screens.devLoginScreen;

import GUI.screens.devPanel.DevPanelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class DevLoginScreenController {

    @FXML
    Button viewStoreAsUserButton;

    @FXML
    Button devSignupButton;

    @FXML
    TextField emailField;

    @FXML
    TextField passwordField;

    int developerID;

    public void viewStoreAsUserButtonPressed(ActionEvent event) throws IOException {
        Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
        Scene loginScene = new Scene(root_login);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void validateLoginDetails(ActionEvent event) throws IOException {
        int flag=0;
        emailField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        passwordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        if(emailField.getText().trim().isEmpty()==true)
        {
            emailField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

            emailField.setPromptText("Email can not be blank");
            flag=1;
        }

        if(passwordField.getText().isEmpty()==true)
        {
            passwordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            passwordField.setPromptText("Password can not be blank");
            flag=1;
        }

        if(flag==0){
            proceedLoginAfterValidation(event);
        }
    }

    public void proceedLoginAfterValidation(ActionEvent event) throws IOException {
        //blInterface.devInterface developer = new businessLayer.Dev();
        blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
        try {
            developerID=developer.authenticateUser(emailField.getText(),passwordField.getText());
            //deets = user.getUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(developerID==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Logging In");
            alert.setHeaderText("Error Verifying Your Credentials");
            //alert.setContentText("Try logging-in with this email");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader devPanelLoader= new FXMLLoader(getClass().getResource("../devPanel/devPanel.fxml"));
            Parent root_devPanel= devPanelLoader.load();
            DevPanelController devPanelController= devPanelLoader.getController();
            devPanelController.setDeveloperID(this.developerID);
            Scene categoriesList = new Scene(root_devPanel);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            //System.out.println(userID+" in signup addUserToDB");
            //window.setUserData(userID);

            window.setScene(categoriesList);
            window.show();
        }

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
