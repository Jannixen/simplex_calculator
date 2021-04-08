import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import static visual.window.ApplicationWindow.makeApplicationWindow;

public class Runner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage mainStage) {
        makeApplicationWindow();
        mainStage.setOnCloseRequest(
                e -> {
                    Platform.exit();
                    System.exit(0);
                });
    }
}
