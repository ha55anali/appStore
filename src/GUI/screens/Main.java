package GUI.screens;

import GUI.screens.loginScreen.LoginScreenController;
import GUI.screens.splashScreen.SplashScreenController;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;


public class Main extends Application{
    private static Stage primaryStage;

    private void setPrimaryStage(Stage stage){
        Main.primaryStage=stage;
    }

    static public Stage getPrimaryStage(){
        return Main.primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("Play Store");

        FXMLLoader loader_splashScreen =new FXMLLoader(SplashScreenController.class.getResource("/GUI/screens/splashScreen/splashScreen.fxml"));
        Parent root_splashScreen = loader_splashScreen.load();
        SplashScreenController splashScreenController = loader_splashScreen.getController();
        ImageView splashScreenLogo = splashScreenController.getLogo();
        Text splashScreenText = splashScreenController.getText();
        viewSplashScreen(primaryStage, splashScreenLogo, splashScreenText, root_splashScreen);

//        FXMLLoader loader = new FXMLLoader(AppListController.class.getResource("/GUI/screens/devLoginScreen/devLoginScreen.fxml"));
//        Parent root = loader.load();
//        primaryStage.setScene(new Scene(root, 1000, 800));
//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void viewSplashScreen(Stage primaryStage, ImageView splashScreenLogo, Text splashScreenText, Parent root_splashScreen){
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), splashScreenLogo);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), splashScreenText);
        fadeIn2.setFromValue(0);
        fadeIn2.setToValue(1);
        fadeIn2.setCycleCount(1);

        //Finish splash with fade out effect
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), splashScreenLogo);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(2), splashScreenText);
        fadeOut2.setFromValue(1);
        fadeOut2.setToValue(0);
        fadeOut2.setCycleCount(1);

        fadeIn.play();
        fadeIn2.play();

        //After fade in, start fade out
        fadeIn.setOnFinished((e) -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event ->
                    fadeOut.play());
            pause.play();
            //fadeOut.play();
        });

        fadeIn2.setOnFinished((e) -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event ->
                    fadeOut2.play());
            pause.play();
            //fadeOut.play();
        });

        //After fade out, load actual content
        fadeOut.setOnFinished((e) -> {
            //viewLoginScreen(primaryStage);
            //Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
            FXMLLoader loader_loginScreen = new FXMLLoader(LoginScreenController.class.getResource("/GUI/screens/loginScreen/loginScreen.fxml"));
            Parent root_loginScreen = null;
            try {
                root_loginScreen = loader_loginScreen.load();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            primaryStage.setScene(new Scene(root_loginScreen, 1000,800));
            primaryStage.show();
        });
        primaryStage.setScene(new Scene(root_splashScreen, 1000, 800));
        primaryStage.show();
        return;
    }

}


