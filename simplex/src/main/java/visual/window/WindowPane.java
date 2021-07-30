package visual.window;

import javafx.scene.paint.Color;

import static visual.window.PanesProperties.MAIN_PANE_HEIGHT;
import static visual.window.PanesProperties.MAIN_PANE_WIDTH;

class WindowPane extends TemplatePane {


    WindowPane() {
        super(0, 0, MAIN_PANE_WIDTH, MAIN_PANE_HEIGHT, Color.AZURE, false);
    }


}
