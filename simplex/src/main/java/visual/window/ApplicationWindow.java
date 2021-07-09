package visual.window;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import visual.window.panes.*;

public final class ApplicationWindow extends Stage {

    public static final ApplicationWindow APPLICATION_WINDOW = new ApplicationWindow();

    private NotificationsPane notificationsPane;
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
        notificationsPane = new NotificationsPane();

        windowPane.getChildren().add(new HeaderPane());
        windowPane.getChildren().add(new EquationsPane());
        windowPane.getChildren().add(notificationsPane);

        this.root.getChildren().add(windowPane);
        this.setScene(scene);
        this.show();
    }

    public NotificationsPane getNotificationsPane() {
        return notificationsPane;
    }
}
