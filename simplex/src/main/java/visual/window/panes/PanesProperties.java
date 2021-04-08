package visual.window.panes;

import static visual.window.WindowProperties.WINDOW_HEIGHT;
import static visual.window.WindowProperties.WINDOW_WIDTH;

abstract class PanesProperties {

    public static final int MAIN_PANE_HEIGHT = WINDOW_HEIGHT;
    public static final int MAIN_PANE_WIDTH = WINDOW_WIDTH;

    public static final int HEADER_PANE_POSITION_X = (int) (0.1 * WINDOW_WIDTH);
    public static final int HEADER_PANE_POSITION_Y = (int) (0.05 * WINDOW_HEIGHT);
    public static final int HEADER_PANE_HEIGHT = (int) (0.2 * WINDOW_HEIGHT);
    public static final int HEADER_PANE_WIDTH = (int) (0.8 * WINDOW_WIDTH);

    public static final int EQUATIONS_PANE_POSITION_X = (int) (0.15 * WINDOW_WIDTH);
    public static final int EQUATIONS_PANE_POSITION_Y = (int) (0.3 * WINDOW_WIDTH);
    public static final int EQUATIONS_PANE_HEIGHT = (int) (0.5 * WINDOW_WIDTH);
    public static final int EQUATIONS_PANE_WIDTH = (int) (0.7 * WINDOW_WIDTH);

    public static final int NOTIFICATIONS_PANE_POSITION_X = (int) (0.15 * WINDOW_WIDTH);
    public static final int NOTIFICATIONS_PANE_POSITION_Y = (int) (0.85 * WINDOW_WIDTH);
    public static final int NOTIFICATIONS_PANE_HEIGHT = (int) (0.1 * WINDOW_WIDTH);
    public static final int NOTIFICATIONS_PANE_WIDTH = (int) (0.7 * WINDOW_WIDTH);

    public static final int TITLE_ICON_POSITION_X = (int) (0.1 * WINDOW_WIDTH);
    public static final int TITLE_ICON_POSITION_Y = (int) (0.05 * WINDOW_HEIGHT);
    public static final int TITLE_ICON_SIZE = 150;
}
