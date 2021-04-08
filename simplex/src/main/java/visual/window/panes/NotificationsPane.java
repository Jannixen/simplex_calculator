package visual.window.panes;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import static visual.window.panes.PanesProperties.*;

public class NotificationsPane extends TemplatePane {

    TextArea notificationArea;

    public NotificationsPane() {
        super(NOTIFICATIONS_PANE_POSITION_X, NOTIFICATIONS_PANE_POSITION_Y, NOTIFICATIONS_PANE_WIDTH,
                NOTIFICATIONS_PANE_HEIGHT, Color.WHITESMOKE, false);
        makeNotificationsPane();
    }

    public void showNotification(String notification) {
        notificationArea.setText(notification);
    }

    private void makeNotificationsPane() {
        getChildren().add(makeNotificationsArea());
    }

    private TextArea makeNotificationsArea() {
        notificationArea = new TextArea("Tutaj wyświetli się komunikat.");
        notificationArea.layoutXProperty().bind(widthProperty().subtract(notificationArea.widthProperty()).divide(2));
        notificationArea.layoutYProperty().bind(heightProperty().subtract(notificationArea.heightProperty()).divide(2));
        notificationArea.setPrefHeight(NOTIFICATIONS_PANE_HEIGHT);
        notificationArea.setPrefWidth(NOTIFICATIONS_PANE_WIDTH);
        notificationArea.setEditable(false);
        return notificationArea;
    }


}
