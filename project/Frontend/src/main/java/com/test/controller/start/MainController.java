package com.test.controller.start;

import com.test.view.GUI;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * 程序初始化控制类
 */

public class MainController {
    private final Stage stage = GUI.getStage();
    private double stageX;
    private double stageY;
    private double screexX;
    private double screenY;

    public void onMousePressed(MouseEvent e)
    {
        stageX = stage.getX();
        stageY = stage.getY();
        screexX = e.getScreenX();
        screenY = e.getScreenY();
    }

    public void onMouseDragged(MouseEvent e)
    {
        stage.setX(e.getScreenX() - screexX + stageX);
        stage.setY(e.getScreenY() - screenY + stageY);
    }

    public void close()
    {
        GUI.close();
    }

    public void minimize()
    {
        GUI.minimize();
    }
}
