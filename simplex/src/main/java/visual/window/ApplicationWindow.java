package visual.window;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class ApplicationWindow extends Stage {

    public static final ApplicationWindow APPLICATION_WINDOW = new ApplicationWindow();

    private final Scene scene;
    private final Group root;
    private NotificationsPane notificationsPane;

    private ApplicationWindow() {
        root = new Group();
        this.scene = new Scene(this.root);
        setTitle("Simplex Calculator");
        buildWindow();
    }

    public static ApplicationWindow getApplicationWindow() {
        return APPLICATION_WINDOW;
    }

    public NotificationsPane getNotificationsPane() {
        return notificationsPane;
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


}
