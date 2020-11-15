package GUI.screens.signupScreen;

import GUI.screens.categoriesList.CategoriesListController;
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

public class SignupScreenController {
    @FXML
    Button loginButton;

    @FXML
    Button signupButton;

    @FXML
    PasswordField passwordField;

    @FXML
    TextField fullnameField;

    @FXML
    TextField emailField;

    @FXML
    PasswordField confirmPasswordField;

    int userID;

    public void loginButtonPressed(ActionEvent event) throws IOException
    {
        Parent root_login = FXMLLoader.load(getClass().getResource("../loginScreen/loginScreen.fxml"));
        Scene loginScene = new Scene(root_login);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
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
        }
    }

    private void proceedSignupAfterValidation(ActionEvent event) throws IOException {

        blInterface.userInterface user = businessLayer.blFactory.getUserObject();
        blInterface.userDetails deets = new blInterface.userDetails(fullnameField.getText(), 4, LocalDate.now().minusYears(20),
                emailField.getText(), passwordField.getText());
        try {
            userID=user.addUser(deets);
            //deets = user.getUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("USER ID: "+userID);

        if(userID<=0){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Signing Up");
            //alert.setHeaderText("Email Already Exists!");
            //alert.setContentText("Try logging-in with this email");
            alert.showAndWait();
        }
        else
        {
            FXMLLoader categoriesListLoader= new FXMLLoader(getClass().getResource("../categoriesList/categoriesList.fxml"));
            Parent root_categoriesList = categoriesListLoader.load();
            CategoriesListController categoriesListController = categoriesListLoader.getController();
            categoriesListController.setUserID(this.userID);
            Scene categoriesList = new Scene(root_categoriesList);

            //This line gets the Stage information
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            System.out.println(userID+" in signup addUserToDB");
            //window.setUserData(userID);

            window.setScene(categoriesList);
            window.show();
        }



//        FXMLLoader afaqPicLoader= new FXMLLoader(getClass().getResource("../afaqPic/afaqPic.fxml"));
//
//        Parent root_afaqPic = afaqPicLoader.load();
//        Scene afaqScene = new Scene(root_afaqPic);
//        Stage afaqStage = new Stage();
//        afaqStage.setScene(afaqScene);
//        afaqStage.show();




    }

    public int getUserID() {
        return userID;
    }
}
