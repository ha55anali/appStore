package GUI.screens.splashScreen;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;
import java.util.Objects;

public class SplashScreenController {

    @FXML private ImageView logo;
    @FXML private Text text;

    public void showSplashScreen(Stage primaryStage) throws IOException {
        try {
            //Load splash screen view FXML
            AnchorPane pane = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource(("GUI/screens/splashScreen/splashScreen.fxml"))));
            //Add it to root container (Can be StackPane, AnchorPane etc)
            //root.getChildren().setAll(pane);
            primaryStage.setScene(new Scene(pane,1000,800));

            //Load splash screen with fade in effect
            FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), logo);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeIn2 = new FadeTransition(Duration.seconds(2), text);
            fadeIn2.setFromValue(0);
            fadeIn2.setToValue(1);
            fadeIn2.setCycleCount(1);

            //Finish splash with fade out effect
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), logo);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            FadeTransition fadeOut2 = new FadeTransition(Duration.seconds(2), text);
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
                return;
            });
        } catch (IOException ex) {
            //Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR");
        }
    }

    public ImageView getLogo(){
        return logo;
    }

    public Text getText(){
        return text;
    }
}
