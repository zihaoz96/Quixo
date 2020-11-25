package model;

import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public interface StateConsole {
	void next(MouseEvent event);
    void prev(MouseEvent event);
    void printStatus(TextArea progress);
}
