package visual.window;

import static visual.window.WindowProperties.WINDOW_HEIGHT;
import static visual.window.WindowProperties.WINDOW_WIDTH;

abstract class PanesProperties {

    static final int MAIN_PANE_HEIGHT = WINDOW_HEIGHT;
    static final int MAIN_PANE_WIDTH = WINDOW_WIDTH;

    static final int HEADER_PANE_POSITION_X = (int) (0.1 * WINDOW_WIDTH);
    static final int HEADER_PANE_POSITION_Y = (int) (0.05 * WINDOW_HEIGHT);
    static final int HEADER_PANE_HEIGHT = (int) (0.25 * WINDOW_HEIGHT);
    static final int HEADER_PANE_WIDTH = (int) (0.8 * WINDOW_WIDTH);
    static final String TITLE = "Simplex Method Calculator";

    static final int EQUATIONS_PANE_POSITION_X = (int) (0.15 * WINDOW_WIDTH);
    static final int EQUATIONS_PANE_POSITION_Y = (int) (0.35 * WINDOW_WIDTH);
    static final int EQUATIONS_PANE_HEIGHT = (int) (0.45 * WINDOW_WIDTH);
    static final int EQUATIONS_PANE_WIDTH = (int) (0.7 * WINDOW_WIDTH);
    static final String OBJECTIVE_EXAMPLE = "5x1 + 10x2 + 8x3";
    static final String CONSTRAINTS_EXAMPLE = "3x1 + 5x2 + 2x3 >= 60 \n 4x1 + 4x2 + 4x3 <= 72 \n 2x1 + 4x2 + 5x3 <= 100";

    static final int NOTIFICATIONS_PANE_POSITION_X = (int) (0.15 * WINDOW_WIDTH);
    static final int NOTIFICATIONS_PANE_POSITION_Y = (int) (0.85 * WINDOW_WIDTH);
    static final int NOTIFICATIONS_PANE_HEIGHT = (int) (0.1 * WINDOW_WIDTH);
    static final int NOTIFICATIONS_PANE_WIDTH = (int) (0.7 * WINDOW_WIDTH);

    static final int TITLE_ICON_SIZE = 150;
}
