package GUI.screens.loginScreen;

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

public class LoginScreenController{

    @FXML
    TextField emailField;

    @FXML
    PasswordField passwordField;

    @FXML
    Button loginButton;

    @FXML
    Button signupButton;

    @FXML
    Button developerToolsButton;

    int userID;


    public void signupButtonPressed(ActionEvent event) throws IOException
    {
        Parent root_signup = FXMLLoader.load(getClass().getResource("../signupScreen/signupScreen.fxml"));
        Scene signupScene = new Scene(root_signup);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(signupScene);
        window.show();
    }

    public void developerToolsButtonPressed(ActionEvent event) throws IOException{
        Parent root_devStartupScreen = FXMLLoader.load(getClass().getResource("../devLoginScreen/devLoginScreen.fxml"));
        Scene scene_devStartupScreen = new Scene(root_devStartupScreen);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(scene_devStartupScreen);
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

            emailField.setPromptText("Username can not be blank");
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

    void proceedLoginAfterValidation(ActionEvent event) throws IOException {
        //blInterface.userInterface user = new businessLayer.User();
        blInterface.userInterface user = businessLayer.blFactory.getUserObject();
//        blInterface.userDetails deets = new blInterface.userDetails(fullnameField.getText(), 4, LocalDate.now().minusYears(20),
//                emailField.getText(), passwordField.getText());
        try {
            userID=user.authenticateUser(emailField.getText(),passwordField.getText());
            //deets = user.getUserDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(userID==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Logging-In");
            alert.setHeaderText("Could not verify your credentials");
            //alert.setContentText("Ooops, there was an error!");

            alert.showAndWait();

        }
        else{
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



    }
}
