package GUI.screens.devSignupScreen;

import GUI.screens.devPanel.DevPanelController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class DevSignupScreenController {

    @FXML
    Button devLoginHereButton;

    @FXML
    TextField fullnameField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    PasswordField confirmPasswordField;

    int developerID;

    public void devLoginHereButtonPressed(ActionEvent event) throws IOException {
        Parent root_devLogin = FXMLLoader.load(getClass().getResource("../devLoginScreen/devLoginScreen.fxml"));
        Scene scene_devLoginScreen = new Scene(root_devLogin);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene_devLoginScreen);
        window.show();
    }

    public void validateSignupDetails(ActionEvent event) throws IOException {
        int flag=0;

        fullnameField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        emailField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        passwordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        confirmPasswordField.setBorder(new Border(new BorderStroke(Color.TRANSPARENT,
                BorderStrokeStyle.NONE, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;

        if(fullnameField.getText().trim().isEmpty()==true)
        {
            fullnameField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            fullnameField.setPromptText("Name can not be blank");
            flag=1;
        }
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
        if(confirmPasswordField.getText().isEmpty()==true)
        {
            confirmPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            confirmPasswordField.setPromptText("Password can not be blank");
            flag=1;
        }
        if(passwordField.getText().equals(confirmPasswordField.getText())==false)
        {
            passwordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            passwordField.clear();

            confirmPasswordField.setBorder(new Border(new BorderStroke(Color.RED,
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));;
            confirmPasswordField.clear();
            passwordField.setPromptText("Invalid Password");
            confirmPasswordField.setPromptText("Invalid Password");
            flag=1;
        }
        if(flag==0)
        {
            proceedSignupAfterValidation(event);
            System.out.println("DEVELOPER ADDED");
        }
    }


    public void proceedSignupAfterValidation(ActionEvent event) throws IOException {
        //add db functionality here
        //blInterface.devInterface developer = new businessLayer.Dev();
        blInterface.devInterface developer = businessLayer.blFactory.getDevObject();
        blInterface.userDetails deets = new blInterface.userDetails(fullnameField.getText(), 4, LocalDate.now().minusYears(20),
                emailField.getText(), passwordField.getText());
        try {
            developerID=developer.addDev(deets);
            //deets = user.getUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Developer ID: "+developerID);

        if(developerID==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Signing Up");
            alert.setHeaderText("Email Already Exists!");
            alert.setContentText("Try logging-in with this email");
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

}
