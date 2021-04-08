package visual.window.panes;

import javafx.scene.paint.Color;

import static visual.window.panes.PanesProperties.MAIN_PANE_HEIGHT;
import static visual.window.panes.PanesProperties.MAIN_PANE_WIDTH;

public class WindowPane extends TemplatePane {


    public WindowPane() {
        super(0, 0, MAIN_PANE_WIDTH, MAIN_PANE_HEIGHT, Color.AZURE, false);
    }


}
