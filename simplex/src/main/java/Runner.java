import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static visual.window.ApplicationWindow.getApplicationWindow;

public class Runner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        getApplicationWindow();
        mainStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                });
    }
}
