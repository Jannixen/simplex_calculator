package visual.window.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

abstract class TemplatePane extends Pane {

    protected TemplatePane(int x, int y, int width, int height, Color color, boolean ifWithBorder) {
        setCoordinates(x, y);
        setPaneSize(width, height);
        setBackgroundColor(color);
        if (ifWithBorder) {
            setBorder();
        }
    }

    private void setPaneSize(int width, int height) {
        setMinSize(width, height);
        setMaxSize(width, height);
    }

    private void setBackgroundColor(Color color) {
        setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setCoordinates(int x, int y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    private void setBorder() {
        setBorder(new Border(new BorderStroke(Color.BLACK,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
    }


}
