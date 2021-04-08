package visual.window;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import visual.window.panes.EquationsPane;
import visual.window.panes.HeaderPane;
import visual.window.panes.NotificationsPane;
import visual.window.panes.WindowPane;

public final class ApplicationWindow extends Stage {

    public static final ApplicationWindow APPLICATION_WINDOW = new ApplicationWindow();

    private Scene scene;
    private Group root = new Group();

    private ApplicationWindow() {
        this.scene = new Scene(this.root);
        buildWindow();
    }

    public static ApplicationWindow makeApplicationWindow() {
        return APPLICATION_WINDOW;
    }

    private void buildWindow() {
        WindowPane windowPane = new WindowPane();

        windowPane.getChildren().add(new HeaderPane());
        windowPane.getChildren().add(new EquationsPane());
        windowPane.getChildren().add(new NotificationsPane());

        this.root.getChildren().add(windowPane);
        this.setScene(scene);
        this.show();
    }


}
